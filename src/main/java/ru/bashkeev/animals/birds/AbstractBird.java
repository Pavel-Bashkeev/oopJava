package ru.bashkeev.animals.birds;

public abstract class AbstractBird {
    private final String name;

    public AbstractBird(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void sing();

    @Override
    public String toString() {
        return name;
    }
}