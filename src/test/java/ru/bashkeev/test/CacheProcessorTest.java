package ru.bashkeev.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import ru.bashkeev.processor.CacheProcessor;

class CacheProcessorTest {

    @BeforeEach
    void setUp() {
        CacheProcessor.clearCache();
    }

    @Test
    @DisplayName("Тест 1: Должен кэшировать все методы когда @Cache без параметров")
    void testCacheAllMethods() throws Exception {
        CacheAllMethodsClass obj = new CacheAllMethodsClass();
        CacheProcessor.cache(obj);

        String result1 = (String) CacheProcessor.executeWithCache(obj, "getAllData");
        assertEquals("All data, call count: 1", result1);
        assertEquals(1, obj.getCallCount());

        String result2 = (String) CacheProcessor.executeWithCache(obj, "getAllData");
        assertEquals("All data, call count: 1", result2);
        assertEquals(1, obj.getCallCount());

        String result3 = (String) CacheProcessor.executeWithCache(obj, "getSpecificData", "test");
        assertEquals("Specific data: test, call count: 2", result3);

        String result4 = (String) CacheProcessor.executeWithCache(obj, "getSpecificData", "test");
        assertEquals("Specific data: test, call count: 2", result4);

        assertTrue(CacheProcessor.getCacheSize() >= 2, "В кэше должно быть минимум 2 записи");
    }

    @Test
    @DisplayName("Тест 2: Должен кэшировать только указанные методы когда @Cache с параметрами")
    void testCacheSpecificMethods() throws Exception {
        CacheSpecificMethodsClass obj = new CacheSpecificMethodsClass();
        CacheProcessor.cache(obj);

        String userResult1 = (String) CacheProcessor.executeWithCache(obj, "getUserInfo", "user123");
        assertEquals("User info for user123, calls: 1", userResult1);

        String userResult2 = (String) CacheProcessor.executeWithCache(obj, "getUserInfo", "user123");
        assertEquals("User info for user123, calls: 1", userResult2);
        assertEquals(1, obj.getUserCallCount());

        String settingsResult1 = (String) CacheProcessor.executeWithCache(obj, "getSettings", "general");
        assertEquals("Settings for general, calls: 1", settingsResult1);

        String settingsResult2 = (String) CacheProcessor.executeWithCache(obj, "getSettings", "general");
        assertEquals("Settings for general, calls: 1", settingsResult2);  // Из кэша!
        assertEquals(1, obj.getSettingsCallCount());  // Счетчик не увеличился!

        String otherResult1 = (String) CacheProcessor.executeWithCache(obj, "getOtherData");
        assertEquals("Other data, calls: 1", otherResult1);

        String otherResult2 = (String) CacheProcessor.executeWithCache(obj, "getOtherData");
        assertEquals("Other data, calls: 2", otherResult2);
        assertEquals(2, obj.getOtherCallCount());
    }

    @Test
    @DisplayName("Тест 3: Должен работать с множественными объектами и игнорировать без @Cache")
    void testMultipleObjectsAndNoCache() throws Exception {
        CacheAllMethodsClass cachedObj = new CacheAllMethodsClass();
        CacheSpecificMethodsClass specificObj = new CacheSpecificMethodsClass();
        NoCacheClass noCacheObj = new NoCacheClass();

        CacheProcessor.cache(cachedObj, specificObj, noCacheObj);

        String result1 = (String) CacheProcessor.executeWithCache(cachedObj, "getAllData");
        String result2 = (String) CacheProcessor.executeWithCache(cachedObj, "getAllData");
        assertEquals(result1, result2);
        assertEquals(1, cachedObj.getCallCount());

        String user1 = (String) CacheProcessor.executeWithCache(specificObj, "getUserInfo", "test");
        String user2 = (String) CacheProcessor.executeWithCache(specificObj, "getUserInfo", "test");
        assertEquals(user1, user2);
        assertEquals(1, specificObj.getUserCallCount());

        String data1 = (String) CacheProcessor.executeWithCache(noCacheObj, "getData");
        String data2 = (String) CacheProcessor.executeWithCache(noCacheObj, "getData");
        assertNotEquals(data1, data2);
        assertEquals(2, noCacheObj.getCallCount());
    }
}