package com.epam.brest.course.client;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.dto.DepartmentAvgSalary;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.Scanner;

/**
 * REST Client console application.
 */
public class DemoApp {

    private DepartmentService departmentService;

    private Scanner sc = new Scanner(System.in);

    public DemoApp(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    private void menu() {
        System.out.println("==========================================");
        System.out.println("|           MENU SELECTION               |");
        System.out.println("==========================================");
        System.out.println("|             OPTIONS                    |");
        System.out.println("     1. Get all departments");
        System.out.println("     2. Get department by ID");
        System.out.println("     3. Add department");
        System.out.println("     4. Exit");
        System.out.println("==========================================");

        int sw = 0;
        while (sw != 4) {
            System.out.print("Select point: ");
            if (sc.hasNextInt()) {
                sw = sc.nextInt();
                try {
                    checkValue(sw);
                } catch (ServerDataAccessExeption e) {
                    System.out.println("RESPONSE ERROR " + e.getMessage());
                }
                checkValue(sw);
            } else {
                System.out.println("bad value " + sc.next());
            }
        }
    }

    private void checkValue(int item) {
        switch (item) {
            case 1:
                getAllDepartments();
                break;
            case 2:
                getDepartmentById();
                break;
            case 3:
                addDepartment();
                break;
            case 4:
                System.out.println("Bye!");
                break;
            default:
                System.out.println("Invalid selection!");
        }
    }

    private void getAllDepartments() {
        Collection<DepartmentAvgSalary> departments
                = departmentService.serviceDepartmentAvgSalary();
        System.out.println("departments: " + departments.toString());
    }

    private void getDepartmentById() {
        System.out.print("Enter department id: ");
        int id = 0;
        if (sc.hasNextInt() && (id = sc.nextInt()) > 0) {
            id = sc.nextInt();
            Department department = departmentService.serviceGetDepartmentById(id);
            System.out.println("department: " + department);
        } else {
            System.out.println("bad value " + sc.next());
        }
    }

    private void addDepartment() {
        System.out.print("Enter department name: ");
        String name = sc.next();

        System.out.print("Enter department description: ");
        String descr = sc.next();

        Department department = new Department(name, descr);
        department = departmentService.serviceAddDepartment(department);
        System.out.println("Was added: " + department);
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx
                = new ClassPathXmlApplicationContext("spring-context.xml");
        DepartmentService department = ctx.getBean(DepartmentService.class);
        DemoApp demoApp = new  DemoApp(department);
        demoApp.menu();
    }

}
