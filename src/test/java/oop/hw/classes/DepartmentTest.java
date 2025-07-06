package oop.hw.classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DepartmentTest {
    @Test
    void testDepartmentCreation() {
        Department dept = new Department("IT");
        assertEquals("IT", dept.getNameDepartment());
        assertNull(dept.getChief());
    }

    @Test
    void testDepartmentWithChief() {
        Employee chief = new Employee("Иван Иванов", null);
        Department dept = new Department("Финансы", chief);

        assertEquals("Финансы", dept.getNameDepartment());
        assertEquals(chief, dept.getChief());
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
}
