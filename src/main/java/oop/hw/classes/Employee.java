package oop.hw.classes;

public class Employee {
    String name;
    Department department;

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
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

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
