package oop.hw.classes;

import java.util.ArrayList;
import java.util.List;

public class Department {
    String nameDepartment;
    Employee chief;
    List<Employee> employees;

    public Department(String nameDepartment, Employee chief) {
        this.nameDepartment = nameDepartment;
        this.chief = chief;
        this.employees = new ArrayList<>();
        if (chief != null) {
            this.employees.add(chief);
            chief.department = this;
        }
    }

    public Department(String nameDepartment) {
        this.nameDepartment = nameDepartment;
        this.employees = new ArrayList<>();
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

    public void setChief(Employee chief) {
        if (chief != null && !employees.contains(chief)) {
            addEmployee(chief);
        }
        this.chief = chief;
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(this.employees);
    }

    public void setAllEmployees(List<Employee> employees) {
        this.employees = employees;
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
