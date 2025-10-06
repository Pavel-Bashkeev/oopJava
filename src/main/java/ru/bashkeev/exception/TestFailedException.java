package ru.bashkeev.exception;

public class TestFailedException extends RuntimeException {
    public TestFailedException(String message) {
        super(message);
    }
}
