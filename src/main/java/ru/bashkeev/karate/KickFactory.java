package ru.bashkeev.karate;

public class KickFactory {
    public static Kick kickLeg() {
        return name -> System.out.println(name + ": бац!");
    }

    public static Kick kickHand() {
        return name -> System.out.println(name + ": кия!");
    }

    public static Kick kickJump() {
        return name -> System.out.println(name + ": вжух!");
    }
}
