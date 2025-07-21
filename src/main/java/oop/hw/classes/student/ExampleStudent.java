package oop.hw.classes.student;

public class ExampleStudent {
    public static void demo() {

        Student vasya = new Student("Вася", grade -> grade == 0 || grade == 1);
        vasya.addGrade(1);
        vasya.addGrade(0);
        // vasya.addGrade(2); // Будет ошибка

        Student petya = new Student("Петя", grade -> grade % 2 == 0);
        petya.addGrade(2);
        petya.addGrade(4);
        // petya.addGrade(3); // Будет ошибка

        System.out.println(vasya);
        System.out.println(petya);
    }
}
