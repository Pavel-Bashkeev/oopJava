package ru.bashkeev.animals.birds;

import java.util.Random;

public class Parrot extends AbstractBird {
    private static final Random random = new Random();
    private final String phrase;

    public Parrot(String name, String phrase) {
        super(name);
        if (phrase == null || phrase.trim().isEmpty()) {
            throw new IllegalArgumentException("Попугай должен иметь фразу для пения");
        }
        this.phrase = phrase.trim();
    }

    public Parrot(String phrase) {
        this("Попугай", phrase);
    }

    @Override
    public void sing() {
        int maxLength = Math.min(phrase.length(), 1 + random.nextInt(phrase.length()));
        System.out.println(phrase.substring(0, maxLength));
    }

    public String getPhrase() {
        return phrase;
    }

    @Override
    public String toString() {
        return super.toString() + " (знает фразу: \"" + phrase + "\")";
    }
}