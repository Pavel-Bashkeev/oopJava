package ru.bashkeev.network;

import ru.bashkeev.exceptions.AlreadyClosedException;
import ru.bashkeev.exceptions.ConnectionLostException;

import java.util.Random;

public class Connection {
    final String address;
    boolean isClosed;
    final Random random;

    public Connection(String address) {
        this.address = address;
        this.isClosed = false;
        this.random = new Random();
    }

    public String getData() throws ConnectionLostException {
        if (isClosed) {
            throw new AlreadyClosedException();
        }

        if (random.nextBoolean()) {
            throw new ConnectionLostException("Потеряно соединение с " + address);
        }

        return "test connection";
    }

    public void close() {
        isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public String getAddress() {
        return address;
    }
}