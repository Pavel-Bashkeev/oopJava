package oop.hw.classes.meowableObject;

import oop.hw.interfaces.Meowable;

public class MeowExecutor {
    public static void makeAllMeow(Meowable[] meowables) {
        for (Meowable meowable : meowables) {
            meowable.meow();
        }
    }
}
