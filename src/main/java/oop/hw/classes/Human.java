package oop.hw.classes;

public class Human {
    String name;
    int height;

    public Human(String name, int height) {
        this.name = name;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Человек с именем \"" + name + "\" и ростом " + height;
    }
}
