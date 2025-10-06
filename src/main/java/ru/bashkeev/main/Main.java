package ru.bashkeev.main;

import ru.bashkeev.test.TestExample;
import ru.bashkeev.utils.TestRunner;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<TestRunner.ResultType, List<TestRunner.TestResult>> results =
                TestRunner.run(TestExample.class, List.of());

        printSimpleStatistics(results);
    }

    private static void printSimpleStatistics(Map<TestRunner.ResultType, List<TestRunner.TestResult>> results) {
        int passed = results.get(TestRunner.ResultType.PASS).size();
        int failed = results.get(TestRunner.ResultType.FAILED).size();
        int errors = results.get(TestRunner.ResultType.ERROR).size();
        int disabled = results.get(TestRunner.ResultType.DISABLED).size();
        int total = passed + failed + errors + disabled;

        System.out.println("=== РЕЗУЛЬТАТЫ ТЕСТИРОВАНИЯ ===");
        System.out.println("Всего тестов: " + total);
        System.out.println("Пройдено: " + passed);
        System.out.println("Провалено: " + failed);
        System.out.println("Ошибок: " + errors);
        System.out.println("Отключено: " + disabled);

        // Выводим список проваленных тестов
        if (failed > 0) {
            System.out.println("\nПроваленные тесты:");
            results.get(TestRunner.ResultType.FAILED).forEach(test ->
                    System.out.println("  - " + test.name() + ": " + test.t().getMessage())
            );
        }

        // Выводим список тестов с ошибками
        if (errors > 0) {
            System.out.println("\nТесты с ошибками:");
            results.get(TestRunner.ResultType.ERROR).forEach(test ->
                    System.out.println("  - " + test.name() + ": " + test.t().getMessage())
            );
        }

        // Итоговый статус
        if (failed == 0 && errors == 0) {
            System.out.println("\n✅ ВСЕ ТЕСТЫ ПРОЙДЕНЫ УСПЕШНО!");
        } else {
            System.out.println("\n❌ ЕСТЬ ПРОБЛЕМЫ В ТЕСТАХ!");
        }
    }
}