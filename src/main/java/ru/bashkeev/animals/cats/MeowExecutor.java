package ru.bashkeev.animals.cats;

public class MeowExecutor {
    public static void makeAllMeow(Meowable[] meowables) {
        for (Meowable meowable : meowables) {
            meowable.meow();
        }
    }
}
