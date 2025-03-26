package main.java.com.university;
import java.sql.*;
import java.util.*;

public class Student extends AcademicPerson implements Printable{
    int studentID;
    String major;
    private double gpa;
    static int totalStudents = 0;
    private String url = "jdbc:mysql://localhost:3306/jspm";
    private String username = "root", password = "1577";

    Student(String name, int studentID, String major){
        this.name = name;
        this.studentID = studentID;
        this.major = major;
        totalStudents++;

        // updating the database
        try{
            // make connection with drivers
            Connection connection = DriverManager.getConnection(url, username, password);

            // prepare statment
            String query = "INSERT INTO Students(Name, Major, StudentID) VALUES(?, ?, ?);";

            // Preapared statement
            PreparedStatement ptst = connection.prepareStatement(query);
            ptst.setString(1, this.name);
            ptst.setString(2, this.major);
            ptst.setInt(3, this.studentID);
            int result = ptst.executeUpdate();
            System.out.println("Updated the student table..");
        }catch(SQLException s){
            System.out.println("SQL exception : "+ s.getStackTrace());
        }
    }

    static int getTotalStudents(){
        return totalStudents;
    }

    void enrollInCourse(String courseName, int courseID, int semster, int year){

        try{
            // build the connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // prepare for the query
            String query = "INSERT INTO Enrollments(StudentID,CourseID,Semester,Year) VALUES(?, ?, ?, ?);";

            // set prepared statement
            PreparedStatement ptst = connection.prepareStatement(query);
            ptst.setInt(1, this.studentID);
            ptst.setInt(2, courseID);
            ptst.setInt(3, semster);
            ptst.setInt(4, year);
            int result = ptst.executeUpdate();
            System.out.println("Updated the enrollments table..");
        }catch(SQLException s){
            System.out.println("SQL exception ; " + s.getMessage());
        }

        System.out.println(name + " enrolled in " + courseName);

    }

    public void setGpa(double gpa){
        if(gpa < 0 || gpa > 10){
            throw new IllegalArgumentException("Invalid GPA");
        }else{
            this.gpa = gpa;

            try{
                Connection connection = DriverManager.getConnection(url, username, password);

                String query = "UPDATE Students SET GPA=? WHERE studentID=?;";

                PreparedStatement ptst = connection.prepareStatement(query);
                ptst.setDouble(1, this.gpa);
                ptst.setInt(2, this.studentID);
                int result = ptst.executeUpdate();
                System.out.println("GPA updated successfully..");

            }catch(SQLException s){
                System.out.println("SQL exception ; " + s.getMessage());
            }
        }
    }

    public double getGpa(){
        return this.gpa;
    }

    @Override
    void displayAcademicDetails(){
        System.out.println("Academic details of Student " + this.name + ", ID : " + this.studentID);
    }

    @Override
    public void printDetails(){
        System.out.println("Student name : " + this.name + ", ID : " + this.studentID);
    }
}
