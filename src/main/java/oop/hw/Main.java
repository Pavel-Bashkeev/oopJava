package oop.hw;

import oop.hw.classes.*;

public class Main {
    public static void main(String[] args) {
        Department department = new Department("IT");

        Employee employeePetrov = new Employee("Петров", department);
        Employee employeeKozlova = new Employee("Козлова", department);
        Employee employeeSidorov = new Employee("Сидоров", department);

        //department.setChief(employeeKozlova);

        System.out.println(employeePetrov);
        System.out.println(employeeKozlova);
        System.out.println(employeeSidorov);
    }
}