package main.java.com.university;
import java.sql.*;

public class Professor extends AcademicPerson{
    String department;
    private String url = "jdbc:mysql://localhost:3306/jspm";
    private String username = "root", password = "1577";

    Professor(String name, String department){
        this.name = name;
        this.department = department;

        try{
            // create a connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // query
            String query = "INSERT INTO Professors(Name, Department) VALUES(?, ?);";

            // Prepare statement
            PreparedStatement ptst = connection.prepareStatement(query);
            ptst.setString(1, this.name);
            ptst.setString(2, this.department);
            int result = ptst.executeUpdate();
            System.out.println("Update the professor table..");
        }catch(SQLException s){
            System.out.println("SQL Exception : " + s.getMessage());
        }
    }

    @Override
    void displayAcademicDetails(){
        System.out.println("Professor Name : " + this.name + ", Department : " + this.department);
    }
}
