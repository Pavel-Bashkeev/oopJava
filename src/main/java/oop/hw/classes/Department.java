package oop.hw.classes;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String nameDepartment;
    private Employee chief;
    private final List<Employee> employees;

    public Department(String nameDepartment, Employee chief) {
        this.nameDepartment = nameDepartment;
        this.employees = new ArrayList<>();
        setChief(chief);
    }

    public Department(String nameDepartment) {
        this(nameDepartment, null);
    }

    public String getNameDepartment() {
        return this.nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public Employee getChief() {
        return this.chief;
    }

    public void setChief(Employee newChief) {
        if (this.chief == newChief) {
            return;
        }

        if (this.chief != null) {
            this.chief = null;
        }

        this.chief = newChief;

        if (this.chief != null) {
            if (this.chief.getDepartment() != this) {
                this.chief.setDepartment(this);
            }
            if (!this.employees.contains(this.chief)) {
                this.employees.add(this.chief);
            }
        }
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(this.employees);
    }

    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
            employee.department = this;
        }
    }

    public void removeEmployee(Employee employee) {
        if (employees.contains(employee)) {
            employees.remove(employee);
            if (chief != null && chief.equals(employee)) {
                chief = null;
            }
            employee.department = null;
        }
    }
}
