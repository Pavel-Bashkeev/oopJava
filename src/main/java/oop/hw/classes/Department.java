package oop.hw.classes;

public class Department {
    String nameDepartment;
    Employee chief;

    public Department(String nameDepartment,  Employee chief) {
        this.nameDepartment = nameDepartment;
        this.chief = chief;
    }

    public Department(String nameDepartment) {
        this.nameDepartment = nameDepartment;
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
        this.chief = chief;
    }
}
