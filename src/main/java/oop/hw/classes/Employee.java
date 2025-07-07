package oop.hw.classes;

import java.util.List;

public class Employee {
    String name;
    Department department;

    public Employee(String name, Department department) {
        this.name = name;
        if (department != null) {
            this.department = department;
            department.addEmployee(this);
        }
    }

    @Override
    public String toString() {
        if (this.department.getChief() != null && this.name.equalsIgnoreCase(this.department.getChief().getName())) {
            return this.name + " начальник отдела " + this.department.getNameDepartment();
        }

        if (this.department.getChief() != null) {
            return this.name + " работает в отделе " + this.department.getNameDepartment() + ", начальник которого " + this.department.getChief().getName();
        }

        return this.name + " работает в отделе " + this.department.getNameDepartment() + ", место начальника которого вакантно";
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department newDepartment) {
        if (this.department == newDepartment) {
            return;
        }

        if (this.department != null) {
            if (this.department.getAllEmployees().contains(this)) {
                this.department.removeEmployee(this);
            }
        }
        this.department = newDepartment;
        if (newDepartment != null) {
            this.department.addEmployee(this);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getDepartmentEmployees() {
        if (department != null) {
            return department.getAllEmployees();
        }

        return List.of();
    }
}
