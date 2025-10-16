package ru.bashkeev.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import ru.bashkeev.processor.CacheProcessor;

class CacheProcessorTest {
    @Test
    @DisplayName("Тест 1: Должен кэшировать все методы когда @Cache без параметров")
    void testCacheAllMethods() {
        CacheAllMethodsClass original = new CacheAllMethodsClass();
        CacheableService cachedService = CacheProcessor.cache(original);

        // Используем прокси как обычный объект
        String result1 = cachedService.getAllData();
        assertEquals("All data, call count: 1", result1);
        assertEquals(1, original.getCallCount());

        String result2 = cachedService.getAllData(); // Должен быть из кэша
        assertEquals("All data, call count: 1", result2);
        assertEquals(1, original.getCallCount()); // Счетчик не увеличился!

        String result3 = cachedService.getSpecificData("test");
        assertEquals("Specific data: test, call count: 2", result3);

        String result4 = cachedService.getSpecificData("test"); // Из кэша
        assertEquals("Specific data: test, call count: 2", result4);
        assertEquals(2, original.getCallCount()); // Счетчик не увеличился!
    }

    @Test
    @DisplayName("Тест 2: Должен кэшировать только указанные методы когда @Cache с параметрами")
    void testCacheSpecificMethods() {
        CacheSpecificMethodsClass original = new CacheSpecificMethodsClass();
        UserService cachedService = CacheProcessor.cache(original);

        // Эти методы должны кэшироваться
        String userResult1 = cachedService.getUserInfo("user123");
        assertEquals("User info for user123, calls: 1", userResult1);

        String userResult2 = cachedService.getUserInfo("user123"); // Из кэша
        assertEquals("User info for user123, calls: 1", userResult2);
        assertEquals(1, original.getUserCallCount()); // Счетчик не увеличился

        String settingsResult1 = cachedService.getSettings("general");
        assertEquals("Settings for general, calls: 1", settingsResult1);

        String settingsResult2 = cachedService.getSettings("general"); // Из кэша
        assertEquals("Settings for general, calls: 1", settingsResult2);
        assertEquals(1, original.getSettingsCallCount()); // Счетчик не увеличился

        // Этот метод НЕ должен кэшироваться
        String otherResult1 = cachedService.getOtherData();
        assertEquals("Other data, calls: 1", otherResult1);

        String otherResult2 = cachedService.getOtherData(); // НЕ из кэша
        assertEquals("Other data, calls: 2", otherResult2);
        assertEquals(2, original.getOtherCallCount()); // Счетчик увеличился!
    }

    @Test
    @DisplayName("Тест 3: Должен работать с множественными объектами и игнорировать без @Cache")
    void testMultipleObjectsAndNoCache() {
        CacheAllMethodsClass cachedOriginal = new CacheAllMethodsClass();
        CacheSpecificMethodsClass specificOriginal = new CacheSpecificMethodsClass();
        NoCacheClass noCacheOriginal = new NoCacheClass();

        // Создаем прокси для объектов с @Cache
        CacheableService cachedObj = CacheProcessor.cache(cachedOriginal);
        UserService specificObj = CacheProcessor.cache(specificOriginal);

        // Объект без @Cache возвращается как есть
        NoCacheClass noCacheObj = CacheProcessor.cache(noCacheOriginal);

        // Проверяем кэширование для объекта с @Cache
        String result1 = cachedObj.getAllData();
        String result2 = cachedObj.getAllData(); // Из кэша
        assertEquals(result1, result2);
        assertEquals(1, cachedOriginal.getCallCount());

        // Проверяем кэширование для объекта с конкретными методами
        String user1 = specificObj.getUserInfo("test");
        String user2 = specificObj.getUserInfo("test"); // Из кэша
        assertEquals(user1, user2);
        assertEquals(1, specificOriginal.getUserCallCount());

        // Проверяем что объект без @Cache не кэшируется
        String data1 = noCacheObj.getData();
        String data2 = noCacheObj.getData(); // НЕ из кэша
        assertNotEquals(data1, data2);
        assertEquals(2, noCacheOriginal.getCallCount());
    }

    @Test
    @DisplayName("Тест 4: Должен корректно обрабатывать методы Object")
    void testObjectMethods() {
        CacheAllMethodsClass original = new CacheAllMethodsClass();
        CacheableService cachedService = CacheProcessor.cache(original);

        // toString() и hashCode() работают
        assertEquals(original.toString(), cachedService.toString());
        assertEquals(original.hashCode(), cachedService.hashCode());

        // equals() хотя бы не падает
        assertDoesNotThrow(() -> cachedService.equals(cachedService));
        assertDoesNotThrow(() -> cachedService.equals(original));
        assertDoesNotThrow(() -> original.equals(cachedService));

        // Дополнительная проверка - методы действительно кэшируются
        String result1 = cachedService.getAllData();
        String result2 = cachedService.getAllData();
        assertEquals(result1, result2); // Из кэша
    }

    @Test
    @DisplayName("Тест 5: Должен различать разные параметры")
    void testDifferentParameters() {
        CacheAllMethodsClass original = new CacheAllMethodsClass();
        CacheableService cachedService = CacheProcessor.cache(original);

        String result1 = cachedService.getSpecificData("param1");
        String result2 = cachedService.getSpecificData("param2"); // Другой параметр
        String result3 = cachedService.getSpecificData("param1"); // Тот же параметр - из кэша

        assertNotEquals(result1, result2);
        assertEquals(result1, result3);
        assertEquals(2, original.getCallCount()); // Вызвалось только 2 раза, третий из кэша
    }
}
