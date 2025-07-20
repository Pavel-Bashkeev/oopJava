package oop.hw.classes.birds;

public class Sparrow extends AbstractBird {
    public Sparrow() {
        super("Воробей");
    }

    @Override
    public void sing() {
        System.out.println("чирик");
    }
}