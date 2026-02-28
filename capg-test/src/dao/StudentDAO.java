package dao;

import model.Student;
import java.sql.SQLException;
import exception.InvalidStudentDataException;
import java.util.List;

public interface StudentDAO {
    void addStudent(Student student) throws SQLException, InvalidStudentDataException;
    List<Student> viewAllStudents() throws SQLException;
    void updateStudent(Student student) throws SQLException, InvalidStudentDataException;
    void deleteStudent(int id) throws SQLException;
}
