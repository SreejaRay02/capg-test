package main;

import dao.*;
import model.Student;
import exception.InvalidStudentDataException;
import java.sql.SQLException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new PostgreSQLStudentDAO();

        while (true) {

            System.out.println("\n1.Add");
            System.out.println("2.View");
            System.out.println("3.Update Email by Mobile");
            System.out.println("4.Delete");
            System.out.println("5.Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {

                switch (choice) {

                    case 1:

                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Mobile (10 digits): ");
                        String mobile = sc.nextLine();

                        dao.addStudent(new Student(id, name, email, age, mobile));
                        System.out.println("Student Added Successfully");
                        break;

                    case 2:

                        List<Student> list = dao.viewAllStudents();
                        if (list.isEmpty()) {
                            System.out.println("No Records Found");
                        } else {
                            list.forEach(st ->
                                    System.out.println(
                                            st.getId() + " | " +
                                            st.getName() + " | " +
                                            st.getEmail() + " | " +
                                            st.getAge() + " | " +
                                            st.getMobile()
                                    )
                            );
                        }
                        break;

                    case 3:

                        System.out.print("Enter New Email: ");
                        String newEmail = sc.nextLine();

                        System.out.print("Enter Mobile Number: ");
                        String ph = sc.nextLine();

                        dao.updateStudent(new Student(0, null, newEmail, 0, ph));
                        System.out.println("Email Updated Successfully");
                        break;

                    case 4:

                        System.out.print("Enter ID to Delete: ");
                        int deleteId = sc.nextInt();
                        sc.nextLine();

                        dao.deleteStudent(deleteId);
                        System.out.println("Student Deleted Successfully");
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice");
                }

            } catch (InvalidStudentDataException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                System.out.println("Database Error");
            }
        }
    }
}