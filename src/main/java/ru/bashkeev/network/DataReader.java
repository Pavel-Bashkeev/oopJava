package ru.bashkeev.network;

import ru.bashkeev.exception.ConnectionLostException;

public class DataReader {

    public static void readAndPrintData() {
        Connection connection = new Connection("ermakov.edu");
        try {
            for (int i = 0; i < 10; i++) {
                try {
                    String data = connection.getData();
                    System.out.println(data);
                } catch (ConnectionLostException e) {
                    System.out.println("Ошибка соединения: " + e.getMessage());
                }
            }
        } finally {
            connection.close();
            System.out.println("Соединение закрыто");
        }
    }
}
