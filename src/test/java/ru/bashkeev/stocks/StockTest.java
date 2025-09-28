package ru.bashkeev.stocks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class StockTest {

    @Test
    void testStockCreation() {
        Stock stock = new Stock("TEST", 100);
        assertEquals("TEST", stock.getName());
        assertEquals(100, stock.getPrice());
    }

    @Test
    void testPriceChangeNotification() {
        Stock stock = new Stock("TEST", 100);
        TestObserver observer = new TestObserver();
        stock.addObserver(observer);

        stock.setPrice(120);
        assertEquals(120, stock.getPrice());
        assertTrue(observer.wasNotified);
    }

    @Test
    void testBotRecommendation() {
        Stock orcl = new Stock("ORCL", 75);
        Bot bot = new Bot();
        orcl.addObserver(bot);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        orcl.setPrice(65);

        assertTrue(out.toString().contains("надо покупать ORCL"));
        System.setOut(System.out);
    }

    @Test
    void testNoNotificationOnSamePrice() {
        Stock stock = new Stock("TEST", 100);
        TestObserver observer = new TestObserver();
        stock.addObserver(observer);

        stock.setPrice(100); // Та же цена
        assertFalse(observer.wasNotified);
    }

    private static class TestObserver implements Observer {
        boolean wasNotified = false;

        @Override
        public void update(Stock stock, int oldPrice) {
            wasNotified = true;
        }
    }
}