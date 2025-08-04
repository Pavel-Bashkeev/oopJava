package ru.bashkeev.main;

import ru.bashkeev.arithmetic.ExampleArithmetic;
import ru.bashkeev.arithmetic.NumberDivider;
import ru.bashkeev.network.DataReader;
import ru.bashkeev.school.ExampleStudent;
import ru.bashkeev.weapons.ExampleShooter;

import static ru.bashkeev.arithmetic.PowerCalculator.calculatePower;
import static ru.bashkeev.school.StudentRecovery.processStudents;

import java.awt.Point;

public class Main {
    public static void main(String[] args) {
//        Example.demo();
//        Example2_6.demo();
//        Example2_7.demo();
      //  ExampleShooter.demo();
        //ExampleStudent.demo();
        // DataReader.readAndPrintData();

//        List<String> input  = Arrays.asList("10", "2", "5", "0", "abc", "3.5");
//        List<Double> result = NumberDivider.divideFirstByNumbers(input);
//
//        System.out.println("Результаты деления:");
//        result.forEach(System.out::println);

        processStudents();
    }
}