package oop.hw.classes.birds;

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