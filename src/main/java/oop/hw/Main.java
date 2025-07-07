package oop.hw;

import oop.hw.classes.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        City A = new City("A");
        City B = new City("B");
        City C = new City("C");
        City D = new City("D");
        City E = new City("E");
        City F = new City("F");

        A.addRoute(B, 500);
        A.addRoute(D, 700);
        A.addRoute(C, 300);

        B.addRoute(A, 500);
        B.addRoute(C, 700);

        C.addRoute(B, 300);
        C.addRoute(D, 700);

        D.addRoute(C, 700);
        D.addRoute(E, 700);
        D.addRoute(A, 700);

        E.addRoute(F, 700);

        F.addRoute(B, 700);
        F.addRoute(E, 700);

        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
        System.out.println(D);
        System.out.println(E);
        System.out.println(F);
    }
}