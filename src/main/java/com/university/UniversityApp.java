package main.java.com.university;

public class UniversityApp {
    public static void main(String[] args) {
//        University university = new University(6020, "SPPU");
        Student stud1 = new Student("Omkar", 72, "Information Technology");
//        Student stud2 = new Student("Vishal", 52, "Mechanical Engineering");

//        university.addStudent(stud1);
//        university.addStudent(stud2);
//
//        stud1.enrollInCourse("Data Structure and Algorithm",1, 2, 3);
//
        try {
            stud1.setGpa(9.8);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
//
//        System.out.println("Student GPA: " + stud1.getGpa());
//        System.out.println("Total Students: " + Student.getTotalStudents());
//
//        Student retrievedStudent = university.getStudentByID(52);
//        if (retrievedStudent != null) {
//            retrievedStudent.printDetails();
//        }
//
//        Professor professor = new Professor("Dr. Smith", "Mathematics");
//        professor.displayAcademicDetails();
//
//        Course<String> course = new Course<>();
//        course.courseDetails = "Advanced Java";
//        System.out.println("Course Details: " + course.courseDetails);
    }
}
