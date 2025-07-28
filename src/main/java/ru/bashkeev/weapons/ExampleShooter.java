package ru.bashkeev.weapons;

public class ExampleShooter {
    public static void demo() {
        Shooter shooter1 = new Shooter("Вася");
        Shooter shooter2 = new Shooter("Петя", new Gun(6, 3));
        Shooter shooter3 = new Shooter("Коля", new AutomaticGun(30, 5));

        System.out.println(shooter1.shoot());
        System.out.println(shooter2.shoot());
        System.out.println(shooter3.shoot());

        System.out.println("\nИнформация о стрелках:");
        System.out.println(shooter1);
        System.out.println(shooter2);
        System.out.println(shooter3);
    }
}
