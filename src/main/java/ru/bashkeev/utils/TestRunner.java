package ru.bashkeev.utils;

import ru.bashkeev.annotation.*;
import ru.bashkeev.exception.TestFailedException;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class TestRunner {

    @SneakyThrows
    public static <T> Map<ResultType, List<TestResult>> run(Class<T> clz, List<String> tags) {
        Constructor<T> constructor = clz.getDeclaredConstructor();
        constructor.setAccessible(true);
        T object = constructor.newInstance();

        Map<ResultType, List<TestResult>> results = new HashMap<>();
        results.put(ResultType.PASS, new ArrayList<>());
        results.put(ResultType.ERROR, new ArrayList<>());
        results.put(ResultType.FAILED, new ArrayList<>());
        results.put(ResultType.DISABLED, new ArrayList<>());

        Method[] allMethods = clz.getDeclaredMethods();

        List<Method> testMethods = Arrays.stream(allMethods)
                .filter(m -> m.isAnnotationPresent(Test.class))
                .toList();

        // TODO: фильтр по тегам
        List<Method> filteredMethods = filterMethodsByTags(testMethods, tags);

        // TODO: сортировка по value в Order
        List<Method> orderedMethods = sortMethodsByOrder(filteredMethods);

        // TODO: сортировка по порядку следования
        List<Method> finalMethods = sortMethodsByDependencies(orderedMethods);

        // @BeforeAll методы
        invokeBeforeAllMethods(allMethods, object);

        for (Method m : finalMethods) {
            if(!m.isAnnotationPresent(Test.class)) continue;
            String testName = getTestName(m);

            // @Disabled TODO
            if (m.isAnnotationPresent(Disabled.class)) {
                results.get(ResultType.DISABLED).add(new TestResult(testName, null));
                continue;
            }

            if (m.isAnnotationPresent(ValueSource.class)) {
                runParameterizedTest(m, object, allMethods, results);
            } else {
                runNormalTest(m, object, allMethods, results);
            }
        }

        // @AfterAll методы
        invokeAfterAllMethods(allMethods, object);

        return results;
    }

    private static void runNormalTest(Method m, Object object, Method[] allMethods,
                                      Map<ResultType, List<TestResult>> results) {
        String testName = getTestName(m);

        try {
            m.setAccessible(true);
            // @BeforeEach методы
            invokeBeforeEachMethods(allMethods, object);

            m.invoke(object);

            // @AfterEach методы
            invokeAfterEachMethods(allMethods, object);

        } catch (InvocationTargetException e) {
            handleTestException(e, testName, results);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        results.get(ResultType.PASS).add(new TestResult(testName, null));
    }

    private static void runParameterizedTest(Method m, Object object, Method[] allMethods,
                                             Map<ResultType, List<TestResult>> results) {
        String baseTestName = getTestName(m);

        List<Object[]> testData = getDataFromValueSource(m);

        if (testData.isEmpty()) {
            results.get(ResultType.ERROR).add(new TestResult(baseTestName,
                    new RuntimeException("No test data provided in @ValueSource")));
            return;
        }

        for (int i = 0; i < testData.size(); i++) {
            Object[] params = testData.get(i);
            String iterationName = baseTestName + " [" + (i + 1) + "/" + testData.size() + "]";

            try {
                m.setAccessible(true);
                // @BeforeEach методы
                invokeBeforeEachMethods(allMethods, object);

                m.invoke(object, params);

                // @AfterEach методы
                invokeAfterEachMethods(allMethods, object);

                results.get(ResultType.PASS).add(new TestResult(iterationName, null));

            } catch (InvocationTargetException e) {
                handleTestException(e, iterationName, results);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static List<Object[]> getDataFromValueSource(Method method) {
        List<Object[]> data = new ArrayList<>();
        ValueSource valueSource = method.getAnnotation(ValueSource.class);
        Parameter[] parameters = method.getParameters();

        if (parameters.length == 1) {
            Class<?> paramType = parameters[0].getType();

            if (paramType == int.class || paramType == Integer.class) {
                Arrays.stream(valueSource.ints())
                        .forEach(value -> data.add(new Object[]{value}));
            } else if (paramType == String.class) {
                Arrays.stream(valueSource.strings())
                        .forEach(value -> data.add(new Object[]{value}));
            } else if (paramType == double.class || paramType == Double.class) {
                Arrays.stream(valueSource.doubles())
                        .forEach(value -> data.add(new Object[]{value}));
            } else if (paramType == boolean.class || paramType == Boolean.class) {
                for (boolean value : valueSource.booleans()) {
                    data.add(new Object[]{value});
                }
            }
        }

        return data;
    }

    private static void handleTestException(InvocationTargetException e, String testName,
                                            Map<ResultType, List<TestResult>> results) {
        if (e.getCause().getClass() == TestFailedException.class) {
            results.get(ResultType.FAILED).add(new TestResult(testName, e.getCause()));
        } else {
            results.get(ResultType.ERROR).add(new TestResult(testName, e.getCause()));
        }
    }

    private static List<Method> filterMethodsByTags(List<Method> methods, List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return methods;
        }

        return methods.stream()
                .filter(method -> {
                    Test testAnnotation = method.getAnnotation(Test.class);
                    if (testAnnotation == null) return false;

                    String[] methodTags = testAnnotation.tags();
                    if (methodTags.length == 0) return true;

                    return Arrays.stream(methodTags)
                            .anyMatch(tags::contains);
                })
                .toList();
    }

    private static List<Method> sortMethodsByOrder(List<Method> methods) {
        return methods.stream()
                .sorted(Comparator.comparingInt(method -> {
                    if (method.isAnnotationPresent(Order.class)) {
                        return method.getAnnotation(Order.class).value();
                    }
                    Test testAnnotation = method.getAnnotation(Test.class);
                    if (testAnnotation != null) {
                        return testAnnotation.order();
                    }
                    return Integer.MAX_VALUE;
                }))
                .toList();
    }

    private static List<Method> sortMethodsByDependencies(List<Method> methods) {
        Map<String, Method> methodMap = new HashMap<>();
        Map<String, List<String>> dependencies = new HashMap<>();

        methods.forEach(method -> {
            String methodName = method.getName();
            methodMap.put(methodName, method);
            Test testAnnotation = method.getAnnotation(Test.class);
            if (testAnnotation != null) {
                dependencies.put(methodName, Arrays.asList(testAnnotation.dependsOn()));
            }
        });

        List<Method> sortedMethods = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Set<String> visiting = new HashSet<>();

        methods.forEach(method -> {
            if (!visited.contains(method.getName())) {
                visitMethod(method.getName(), methodMap, dependencies, visited, visiting, sortedMethods);
            }
        });

        return sortedMethods;
    }

    private static void visitMethod(String methodName, Map<String, Method> methodMap,
                                    Map<String, List<String>> dependencies,
                                    Set<String> visited, Set<String> visiting,
                                    List<Method> sortedMethods) {
        if (visited.contains(methodName)) return;
        if (visiting.contains(methodName)) {
            throw new RuntimeException("callback hell: " + methodName);
        }

        visiting.add(methodName);

        List<String> deps = dependencies.getOrDefault(methodName, Collections.emptyList());
        deps.stream()
                .filter(methodMap::containsKey)
                .forEach(dep -> visitMethod(dep, methodMap, dependencies, visited, visiting, sortedMethods));

        visiting.remove(methodName);
        visited.add(methodName);
        sortedMethods.add(methodMap.get(methodName));
    }

    private static String getTestName(Method method) {
        Test test = method.getAnnotation(Test.class);
        return test.name().isEmpty() ? method.getName() : test.name();
    }

    private static void invokeBeforeEachMethods(Method[] allMethods, Object object) {
        Arrays.stream(allMethods)
                .filter(m -> m.isAnnotationPresent(BeforeEach.class))
                .forEach(m -> {
                    try {
                        m.setAccessible(true);
                        m.invoke(object);
                    } catch (Exception e) {
                        System.err.println("Ошибка в @BeforeEach методе " + m.getName() + ": " + e.getMessage());
                    }
                });
    }

    private static void invokeAfterEachMethods(Method[] allMethods, Object object) {
        Arrays.stream(allMethods)
                .filter(m -> m.isAnnotationPresent(AfterEach.class))
                .forEach(m -> {
                    try {
                        m.setAccessible(true);
                        m.invoke(object);
                    } catch (Exception e) {
                        System.err.println("Ошибка в @AfterEach методе " + m.getName() + ": " + e.getMessage());
                    }
                });
    }

    private static void invokeBeforeAllMethods(Method[] allMethods, Object object) {
        Arrays.stream(allMethods)
                .filter(m -> m.isAnnotationPresent(BeforeAll.class))
                .forEach(m -> {
                    try {
                        m.setAccessible(true);
                        m.invoke(object);
                    } catch (Exception e) {
                        System.err.println("Ошибка в @BeforeAll методе " + m.getName() + ": " + e.getMessage());
                    }
                });
    }

    private static void invokeAfterAllMethods(Method[] allMethods, Object object) {
        Arrays.stream(allMethods)
                .filter(m -> m.isAnnotationPresent(AfterAll.class))
                .forEach(m -> {
                    try {
                        m.setAccessible(true);
                        m.invoke(object);
                    } catch (Exception e) {
                        System.err.println("Ошибка в @AfterAll методе " + m.getName() + ": " + e.getMessage());
                    }
                });
    }
    public record TestResult(String name, Throwable t) {}

    public enum ResultType {
        PASS, FAILED, ERROR, DISABLED
    }
}

