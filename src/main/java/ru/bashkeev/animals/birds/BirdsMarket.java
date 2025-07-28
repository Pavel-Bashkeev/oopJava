package ru.bashkeev.animals.birds;

public class BirdsMarket {
    public static void makeBirdsSing(AbstractBird[] birds) {
        for (AbstractBird bird : birds) {
            bird.sing();
        }
    }
}