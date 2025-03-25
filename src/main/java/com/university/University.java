package main.java.com.university;

import java.util.*;
import java.sql.*;

public class University {
    ArrayList<Student> students = new ArrayList<>();
    HashMap<Integer, Student> studentMap = new HashMap<>();
    int universityID;
    String universityName;
    // declaring the url
    private String url = "jdbc:mysql://localhost:3306/jspm";
    private String username = "root", password = "1577";

    University(int universityID, String universityName){

        // updating the university details
        this.universityID = universityID;
        this.universityName = universityName;


        // String databaseQuery = "CREATE DATABASE IF NOT EXISTS " + universityName;
        String[] tableQuery = {"CREATE TABLE IF NOT EXISTS University(universityID INT PRIMARY KEY, universityName VARCHAR(100));",
                "CREATE TABLE IF NOT EXISTS University_Students (ID INT PRIMARY KEY AUTO_INCREMENT,  UniversityID INT,  StudentID INT);",
                "CREATE TABLE IF NOT EXISTS Enrollments (EnrollmentID INT PRIMARY KEY AUTO_INCREMENT,  StudentID INT,  CourseID INT,  Semester VARCHAR(10),  Year YEAR);",
                "CREATE TABLE IF NOT EXISTS Courses (CourseID INT PRIMARY KEY AUTO_INCREMENT, CourseName VARCHAR(100), CourseCode VARCHAR(10) UNIQUE ); ",
                "CREATE TABLE IF NOT EXISTS Professors (ProfessorID INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(100), Department VARCHAR(100) ); ",
                "CREATE TABLE IF NOT EXISTS Students (StudentID INT PRIMARY KEY AUTO_INCREMENT,Name VARCHAR(100),Major VARCHAR(100),GPA DECIMAL(3,2) CHECK(GPA BETWEEN 0 AND 10));"
        };
        String uniQuery = "INSERT INTO University(universityID, universityName) VALUES(?, ?);";

        try{
            // load the drivers for database
            Class.forName("com.mysql.cj.jdbc.Driver");

            //establish the connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // creating statement
            Statement st = connection.createStatement();

            // creating all tables
            for(String query : tableQuery){
                int result = st.executeUpdate(query);
            }
            System.out.println("Tables created successfully..");

            // prepare statement for university table
            PreparedStatement ptst = connection.prepareStatement(uniQuery);
            ptst.setInt(1, universityID);
            ptst.setString(2, universityName);
            int number = ptst.executeUpdate();
            System.out.println("Updated the university table...");

        }catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
        }catch(SQLException s){
            System.out.println(s.getMessage());
        }
    }

    void addStudent(Student student){
        students.add(student);
        studentMap.put(student.studentID, student);

        try{
            // establish the connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // prepare statement
            String query = "INSERT INTO University_Students(UniversityID, StudentID) VALUES(?, ?);";

            // Prepared statement
            PreparedStatement ptst = connection.prepareStatement(query);
            ptst.setInt(1, this.universityID);
            ptst.setInt(2, student.studentID);
            int result = ptst.executeUpdate();
            System.out.println("Updated the student-university table..");
        }catch(SQLException s){
            System.out.println("The sql exception : " + s.getMessage());
        }
    }

    Student getStudentByID(int id){
        return studentMap.get(id);
    }
}
