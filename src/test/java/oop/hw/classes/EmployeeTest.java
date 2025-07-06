package oop.hw.classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testEmployeeCreation() {
        Department dept = new Department("Логистика");
        Employee emp = new Employee("Анна Сидорова", dept);

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
        Employee chief = new Employee("АЛексей Новиков", dept);
        dept.setChief(chief);
        Employee emp = new Employee("алексей новиков", dept);

        assertEquals("алексей новиков начальник отдела Аналитика",
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
}