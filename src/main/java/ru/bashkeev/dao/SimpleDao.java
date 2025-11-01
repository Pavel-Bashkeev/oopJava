package ru.bashkeev.dao;

import ru.bashkeev.dao.entity.Department;
import ru.bashkeev.dao.entity.Employee;
import ru.bashkeev.dao.service.DepartmentService;
import ru.bashkeev.dao.service.EmployeeService;

public class SimpleDao {
    public static void main(String[] args) {
        DepartmentService departmentService = new DepartmentService();
        EmployeeService employeeService = new EmployeeService();

        System.out.println("=== DAO Homework Demo ===");

        // 1. Начальное состояние
        System.out.println("\n1. Initial departments:");
        printDepartments(departmentService);
        System.out.println("\n   Initial employees:");
        printEmployees(employeeService);

        // 2. Создаем отделы
        System.out.println("\n2. Creating departments...");
        Department itDept = departmentService.createDepartment(new Department(null, "IT Department"));
        Department hrDept = departmentService.createDepartment(new Department(null, "HR Department"));
        System.out.println("   Created: IT (ID: " + itDept.getId() + "), HR (ID: " + hrDept.getId() + ")");

        // 3. Создаем сотрудников
        System.out.println("\n3. Creating employees...");
        Employee emp1 = employeeService.createEmployee(new Employee(null, "John", "Doe", "john.doe@company.com", itDept.getId()));
        Employee emp2 = employeeService.createEmployee(new Employee(null, "Jane", "Smith", "jane.smith@company.com", hrDept.getId()));
        System.out.println("   Created: " + emp1.getFirstName() + " (ID: " + emp1.getId() + "), " +
                emp2.getFirstName() + " (ID: " + emp2.getId() + ")");

        // 4. Показываем состояние после создания
        System.out.println("\n4. Departments after creation:");
        printDepartments(departmentService);
        System.out.println("\n   Employees after creation:");
        printEmployees(employeeService);

        // 5. Поиск операций
        System.out.println("\n5. Search operations:");
        departmentService.getDepartmentById(itDept.getId())
                .ifPresent(dept -> System.out.println("   Found department: " + dept.getName()));

        employeeService.getEmployeeByEmail("jane.smith@company.com")
                .ifPresent(emp -> System.out.println("   Found employee: " + emp.getFirstName() + " " + emp.getLastName()));

        // 6. Обновления
        System.out.println("\n6. Updating entities...");
        hrDept.setName("Human Resources");
        departmentService.updateDepartment(hrDept);
        System.out.println("   Updated department: " + hrDept.getName());

        emp1.setLastName("Johnson");
        employeeService.updateEmployee(emp1);
        System.out.println("   Updated employee: " + emp1.getFirstName() + " " + emp1.getLastName());

        // 7. Финальный результат
        System.out.println("\n7. Final departments list:");
        printDepartments(departmentService);
        System.out.println("\n   Final employees list:");
        printEmployees(employeeService);

        System.out.println("\n=== Demo completed successfully ===");
    }

    private static void printDepartments(DepartmentService service) {
        var departments = service.getAllDepartments();
        if (departments.isEmpty()) {
            System.out.println("   No departments found");
        } else {
            departments.forEach(dept ->
                    System.out.println("   - " + dept.getId() + ": " + dept.getName()));
        }
    }

    private static void printEmployees(EmployeeService service) {
        var employees = service.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("   No employees found");
        } else {
            employees.forEach(emp ->
                    System.out.println("   - " + emp.getId() + ": " + emp.getFirstName() + " " +
                            emp.getLastName() + " (" + emp.getEmail() + ") Dept: " + emp.getDepartmentId()));
        }
    }
}
