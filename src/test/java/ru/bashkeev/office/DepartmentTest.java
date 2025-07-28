package ru.bashkeev.office;  // Тесты в том же пакете для доступа к package-private методам

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    @Test
    void testDepartmentCreation() {
        Department dept = new Department("IT");
        assertEquals("IT", dept.getNameDepartment());
        assertNull(dept.getChief());
        assertTrue(dept.getAllEmployees().isEmpty());
    }

    @Test
    void testDepartmentWithChief() {
        Department dept = new Department("Финансы");
        Employee chief = new Employee("Иван Иванов", dept);
        dept.setChief(chief);

        assertEquals("Финансы", dept.getNameDepartment());
        assertEquals(chief, dept.getChief());
        assertEquals(1, dept.getAllEmployees().size());
        assertTrue(dept.getAllEmployees().contains(chief));
        assertEquals(dept, chief.getDepartment());
    }

    @Test
    void testDepartmentNameChange() {
        Department dept = new Department("HR");
        dept.setNameDepartment("Кадры");
        assertEquals("Кадры", dept.getNameDepartment());
    }

    @Test
    void testChiefReassignment() {
        Department dept = new Department("Разработка");
        Employee chief1 = new Employee("Петр Петров", dept);
        Employee chief2 = new Employee("Анна Сидорова", dept);

        dept.setChief(chief1);
        assertEquals(chief1, dept.getChief());
        assertEquals(dept, chief1.getDepartment());
        assertEquals(2, dept.getAllEmployees().size());

        dept.setChief(chief2);
        assertEquals(chief2, dept.getChief());
        assertEquals(dept, chief2.getDepartment());
        assertEquals(2, dept.getAllEmployees().size());
        assertTrue(dept.getAllEmployees().contains(chief1));
        assertTrue(dept.getAllEmployees().contains(chief2));

        dept.setChief(null);
        assertNull(dept.getChief());
        assertEquals(2, dept.getAllEmployees().size());
    }

    @Test
    void testEmployeeManagement() {
        Department dept = new Department("Маркетинг");
        Employee emp1 = new Employee("Сергей Сергеев", dept);
        Employee emp2 = new Employee("Ольга Олегова", null);

        assertEquals(1, dept.getAllEmployees().size());
        assertTrue(dept.getAllEmployees().contains(emp1));

        emp2.setDepartment(dept);
        assertEquals(2, dept.getAllEmployees().size());
        assertTrue(dept.getAllEmployees().contains(emp2));

        dept.removeEmployee(emp1);
        assertEquals(1, dept.getAllEmployees().size());
        assertFalse(dept.getAllEmployees().contains(emp1));
        assertNull(emp1.getDepartment());
    }

    @Test
    void testEmployeeToString() {
        Department dept = new Department("Бухгалтерия");
        Employee chief = new Employee("Мария Иванова", dept);
        dept.setChief(chief);
        Employee emp = new Employee("Алексей Петров", dept);

        assertEquals("Мария Иванова начальник отдела Бухгалтерия", chief.toString());
        assertEquals("Алексей Петров работает в отделе Бухгалтерия, начальник которого Мария Иванова", emp.toString());
    }
}