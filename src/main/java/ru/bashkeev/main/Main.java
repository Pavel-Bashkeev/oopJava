package ru.bashkeev.main;

import ru.bashkeev.arithmetic.ExampleArithmetic;
import ru.bashkeev.school.ExampleStudent;

import static ru.bashkeev.arithmetic.PowerCalculator.calculatePower;

import java.awt.Point;

public class Main {
    public static void main(String[] args) {
//        Example.demo();
//        Example2_6.demo();
//        Example2_7.demo();
      //  ExampleShooter.demo();
        //ExampleStudent.demo();
        try {
            double result = calculatePower(args[0], args[1]);
            System.out.println(args[0] + " в степени " + args[1] + " = " + result);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: оба аргумента должны быть целыми числами");
        }

        ru.bashkeev.geometry.points.Point myPoint = new ru.bashkeev.geometry.points.Point(3,4);
        Point awtPoint = new Point(3, 8);

        System.out.println("myPoint точка: " + myPoint);
        System.out.println("Точка из java.awt: " + awtPoint);


        System.out.println("Расстояние от точки myPoint до (0,0): " +
                myPoint.distanceTo(new ru.bashkeev.geometry.points.Point(0, 0)));
        System.out.println("Расстояние от AWT точки до (0,0): " +
                awtPoint.distance(0, 0));
    }
}