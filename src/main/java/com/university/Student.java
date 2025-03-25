package main.java.com.university;
// import java.lang.*;

public class Student extends AcademicPerson implements Printable{
    int studentID;
    String major;
    private double gpa;
    static int totalStudents = 0;

    Student(String name, int studentID, String major){
        this.name = name;
        this.studentID = studentID;
        this.major = major;
        totalStudents++;

        // updating the database

    }

    static int getTotalStudents(){
        return totalStudents;
    }

    void enrollInCourse(String courseName){
        System.out.println(name + " enrolled in " + courseName);
    }

    public void setGpa(double gpa){
        if(gpa < 0 || gpa > 10){
            throw new IllegalArgumentException("Invalid GPA");
        }else{
            this.gpa = gpa;
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
