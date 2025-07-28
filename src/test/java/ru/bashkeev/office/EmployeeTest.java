package ru.bashkeev.office;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EmployeeTest {

    @Test
    void testEmployeeCreation() {
        Department dept = new Department("Логистика");
        Employee   emp  = new Employee("Анна Сидорова", dept);

        assertEquals("Анна Сидорова", emp.getName());
        assertEquals(dept, emp.getDepartment());
    }

    @Test
    void testEmployeeToStringRegular() {
        Department dept = new Department("Маркетинг");
        Employee emp = new Employee("Мария Козлова", dept);

        assertEquals("Мария Козлова работает в отделе Маркетинг, место начальника которого вакантно",
                emp.toString());
    }

    @Test
    void testEmployeeToStringWithChief() {
        Department dept = new Department("Продажи");
        Employee chief = new Employee("Олег Кузнецов", dept);
        dept.setChief(chief);
        Employee emp = new Employee("Елена Воробьева", dept);

        assertEquals("Елена Воробьева работает в отделе Продажи, начальник которого Олег Кузнецов",
                emp.toString());
    }

    @Test
    void testEmployeeToStringIsChief() {
        Department dept = new Department("Разработка");
        Employee chief = new Employee("Сергей Петров", dept);
        dept.setChief(chief);

        assertEquals("Сергей Петров начальник отдела Разработка",
                chief.toString());
    }

    @Test
    void testEmployeeToStringCaseInsensitive() {
        Department dept = new Department("Аналитика");
        Employee chief = new Employee("Алексей Новиков", dept);
        dept.setChief(chief);
        Employee emp = new Employee("алексей новиковв", dept);

        assertEquals("алексей новиковв работает в отделе Аналитика, начальник которого Алексей Новиков",
                emp.toString());
    }

    @Test
    void testSetters() {
        Department dept1 = new Department("Бухгалтерия");
        Department dept2 = new Department("Юридический");
        Employee emp = new Employee("Дмитрий Соколов", dept1);

        emp.setName("Дмитрий Иванов");
        emp.setDepartment(dept2);

        assertEquals("Дмитрий Иванов", emp.getName());
        assertEquals(dept2, emp.getDepartment());
    }

    @Test
    void testGetAllEmployees() {
        Department it = new Department("IT");
        Employee chief = new Employee("Иван Иванов", it);
        it.setChief(chief);

        Employee emp1 = new Employee("Петр Петров", it);

        System.out.println(emp1.getDepartmentEmployees());

        Department dept1 = new Department("Бухгалтерия");
        Department dept2 = new Department("Юридический");
        Employee emp = new Employee("Дмитрий Соколов", dept1);
        Employee emp3 = new Employee("Дмитрий Соколов", dept2);

        emp.setName("Дмитрий Иванов");

        System.out.println(emp.getDepartmentEmployees());
        System.out.println(emp3.getDepartmentEmployees());
    }

    @Test
    void testBecomeChief() {
        Department dept = new Department("Отдел");
        Employee emp = new Employee("Сотрудник", dept);

        dept.setChief(emp);
        assertEquals(emp, dept.getChief());
        assertEquals("Сотрудник начальник отдела Отдел", emp.toString());
    }

    @Test
    void testStopBeingChief() {
        Department dept = new Department("Отдел");
        Employee emp = new Employee("Бывший начальник", dept);
        dept.setChief(emp);

        dept.setChief(null);
        assertNull(dept.getChief());
        assertEquals("Бывший начальник работает в отделе Отдел, место начальника которого вакантно",
                emp.toString());
    }
}