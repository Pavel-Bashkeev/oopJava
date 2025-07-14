package oop.hw;

import oop.hw.classes.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gun gun = new Gun(3);

        for (int i = 0; i < 5; i++) {
            System.out.println(gun.shoot());
        }
    }
}