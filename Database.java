package univdbb;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private final String url = "jdbc:sqlserver://DESKTOP-5L792I5\\SQLEXPRESS;databaseName=univdbb;encrypt=false";
    private final String user = "sa";   
    private final String password = "sasa"; 

   
    public Connection connect() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(" error connection: " + e.getMessage());
            return null;
        }
    }

    
    public void insertStudent(Student s) {
        String sql = "INSERT INTO students(name, age) VALUES(?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
              
            stmt.setString(1, s.getName());
            stmt.setInt(2, s.getAge());
             
            stmt.executeUpdate();
            System.out.println("  adding succsasfuly of student: " + s.getName());
        } catch (SQLException e) {
            System.out.println("  error of data: " + e.getMessage());
        }
    }
    
    public void deleteStudentById(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Student with ID " + id + " deleted successfully.");
            } else {
                System.out.println("Student with ID " + id + " not found in database.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

         
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
              
            while (rs.next()) {
                Student s = new Student(rs.getString("name"), rs.getInt("age"));
                students.add(s);
            }
        } catch (SQLException e) {
            System.out.println(" error in reading : " + e.getMessage());
        }
        return students;
    }
}