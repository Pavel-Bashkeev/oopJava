package oop.hw.classes.birds;

import java.util.Random;

public class Cuckoo extends AbstractBird {
    private static final Random random = new Random();

    public Cuckoo() {
        super("Кукушка");
    }

    @Override
    public void sing() {
        int count = 1 + random.nextInt(10);
        for (int i = 0; i < count; i++) {
            System.out.print("ку-ку");
            if (i < count - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}