package dao;

import model.Student;
import java.sql.*;
import java.util.*;
import exception.InvalidStudentDataException;

public class PostgreSQLStudentDAO implements StudentDAO {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/testdb1",
                "postgres",
                "02102004"
        );
    }

    private void validate(Student s) throws InvalidStudentDataException {
        if (s.getName() == null || s.getName().trim().isEmpty() || s.getName().matches("\\d+"))
            throw new InvalidStudentDataException("Invalid Name");
        if (s.getEmail() == null || !s.getEmail().contains("@"))
            throw new InvalidStudentDataException("Invalid Email");
        if (s.getAge() <= 0)
            throw new InvalidStudentDataException("Invalid Age");
        if (s.getMobile() == null || !s.getMobile().matches("\\d{10}"))
            throw new InvalidStudentDataException("Invalid Mobile");
    }

    public void addStudent(Student s) throws SQLException, InvalidStudentDataException {
        validate(s);
        String sql = "insert into student(id,name,email,age,mobile) values(?,?,?,?,?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getEmail());
            ps.setInt(4, s.getAge());
            ps.setString(5, s.getMobile());
            ps.executeUpdate();
        }
    }

    public List<Student> viewAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "select id,name,email,age,mobile from student";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("mobile")
                ));
            }
        }
        return list;
    }

    public void updateStudent(Student s) throws SQLException, InvalidStudentDataException {
        validate(s);
        String sql = "update student set name=?,email=?,age=?,mobile=? where id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getMobile());
            ps.setInt(5, s.getId());
            ps.executeUpdate();
        }
    }

    public void deleteStudent(int id) throws SQLException {
        String sql = "delete from student where id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}