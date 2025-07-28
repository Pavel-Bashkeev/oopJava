package ru.bashkeev.time;

public class Time {
    int timeInSeconds;

    public Time(int timeInSeconds) {
        if (timeInSeconds < 0) {
            throw new IllegalArgumentException("Время не может быть отрицательным");
        }

        this.timeInSeconds = timeInSeconds;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", getHour(), getMinute(), getSecond());
    }

    private int getHour() {
        int hour = timeInSeconds / 3600;

        if(hour >= 24) {
            hour = hour - 24;
        }

        return hour;
    }

    private int getMinute() {
        return (timeInSeconds % 3600) / 60;
    }

    private int getSecond() {
        return timeInSeconds % 60;
    }
}
