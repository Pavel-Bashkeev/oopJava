package oop.hw.classes;

import org.junit.jupiter.api.Test;
import ru.bashkeev.office.Department;
import ru.bashkeev.office.Employee;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentTest {
    @Test
    void testDepartmentCreation() {
        Department dept = new Department("IT");
        assertEquals("IT", dept.getNameDepartment());
        assertNull(dept.getChief());
        assertTrue(dept.getAllEmployees().isEmpty());
    }

    @Test
    void testDepartmentWithChief() {
        Employee   chief = new Employee("Иван Иванов", null);
        Department dept  = new Department("Финансы", chief);

        assertEquals("Финансы", dept.getNameDepartment());
        assertEquals(chief, dept.getChief());
        assertEquals(1, dept.getAllEmployees().size());
        assertEquals(chief, dept.getAllEmployees().getFirst());
        assertEquals(dept, chief.getDepartment());
    }

    @Test
    void testSetters() {
        Department dept = new Department("HR");
        Employee newChief = new Employee("Петр Петров", null);

        dept.setNameDepartment("Кадры");
        dept.setChief(newChief);

        assertEquals("Кадры", dept.getNameDepartment());
        assertEquals(newChief, dept.getChief());
    }

    @Test
    void testSetChief() {
        Department dept = new Department("HR");
        Employee chief1 = new Employee("Петр Петров", null);
        Employee chief2 = new Employee("Анна Сидорова", null);

        dept.setChief(chief1);
        assertEquals(chief1, dept.getChief());
        assertEquals(dept, chief1.getDepartment());
        assertEquals(1, dept.getAllEmployees().size());

        dept.setChief(chief2);
        assertEquals(chief2, dept.getChief());
        assertEquals(dept, chief1.getDepartment());
        assertEquals(dept, chief2.getDepartment());
        assertEquals(2, dept.getAllEmployees().size());

        dept.setChief(null);
        assertNull(dept.getChief());
        assertEquals(dept, chief2.getDepartment());
        assertEquals(2, dept.getAllEmployees().size());
    }
}
