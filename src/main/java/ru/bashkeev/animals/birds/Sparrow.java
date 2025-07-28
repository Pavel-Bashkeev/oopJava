package ru.bashkeev.animals.birds;

public class Sparrow extends AbstractBird {
    public Sparrow() {
        super("Воробей");
    }

    public Sparrow(String name) {
        super(name);
    }

    @Override
    public void sing() {
        System.out.println("чирик");
    }
}