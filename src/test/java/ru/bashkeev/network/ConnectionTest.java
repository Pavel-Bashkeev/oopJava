package ru.bashkeev.network;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import ru.bashkeev.exceptions.AlreadyClosedException;
import ru.bashkeev.exceptions.ConnectionLostException;

public class ConnectionTest {
    private Connection connection;
    private static final String TEST_ADDRESS = "test.example.com";

    @BeforeEach
    void setUp() {
        connection = new Connection(TEST_ADDRESS);
    }

    @Test
    void testConstructor() {
        assertNotNull(connection);
        assertEquals(TEST_ADDRESS, connection.getAddress());
        assertFalse(connection.isClosed());
    }

    @Test
    void testGetDataSuccess() throws ConnectionLostException {
        boolean successAtLeastOnce = false;
        for (int i = 0; i < 20; i++) {
            try {
                String data = connection.getData();
                assertEquals("test connection", data);
                successAtLeastOnce = true;
            } catch (ConnectionLostException ignored) {}
        }
        assertTrue(successAtLeastOnce, "Должен быть хотя бы один успешный вызов getData()");
    }

    @Test
    void testGetDataConnectionLost() {
        boolean exceptionAtLeastOnce = false;
        for (int i = 0; i < 20; i++) {
            try {
                connection.getData();
            } catch (ConnectionLostException e) {
                assertEquals("Потеряно соединение с " + TEST_ADDRESS, e.getMessage());
                exceptionAtLeastOnce = true;
            }
        }
        assertTrue(exceptionAtLeastOnce, "Должно быть хотя бы одно исключение ConnectionLostException");
    }

    @Test
    void testClose() {
        connection.close();
        assertTrue(connection.isClosed());
    }

    @Test
    void testGetDataAfterClose() {
        connection.close();
        assertThrows(AlreadyClosedException.class, () -> connection.getData());
    }

    @Test
    void testRepeatedClose() {
        connection.close();
        connection.close();
        assertTrue(connection.isClosed());
    }

    @Test
    void testGetAddress() {
        assertEquals(TEST_ADDRESS, connection.getAddress());
    }
}