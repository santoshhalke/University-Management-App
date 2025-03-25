package main.java.com.university;

import java.util.*;

public class University {
    static int totalStudents = 0;
    ArrayList<Student> students = new ArrayList<>();
    HashMap<Integer, Student> studentMap = new HashMap<>();

    University(){
        totalStudents++;
    }

    static int getTotalStudents(){
        return totalStudents;
    }

    void addStudent(Student student){
        students.add(student);
        studentMap.put(student.studentID, student);
    }

    Student getStudentByID(int id){
        return studentMap.get(id);
    }
}
