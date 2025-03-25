package main.java.com.university;

public class Professor extends AcademicPerson{
    String department;

    Professor(String name, String department){
        this.name = name;
        this.department = department;
    }

    @Override
    void displayAcademicDetails(){
        System.out.println("Professor Name : " + this.name + ", Department : " + this.department);
    }
}
