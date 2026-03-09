package com.capgemini.spring_06_03_26;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean-config.xml");

        Employee emp = (Employee) context.getBean("employee");

        System.out.println("Employee ID: " + emp.getEmployeeId());
        System.out.println("Employee Name: " + emp.getEmployeeName());
        System.out.println("Salary: " + emp.getSalary());
        System.out.println("Department ID: " + emp.getDepartment().getDepartmentId());
        System.out.println("Department Name: " + emp.getDepartment().getDepartmentName());
    }
}