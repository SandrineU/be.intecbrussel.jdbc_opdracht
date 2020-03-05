package be.intecbrussel;

import java.sql.*;

public class UpdateData {
    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_store","root","root");
            Statement stmt = conn.createStatement()){

            //hiet gaat de 'null' waarde vervangen worden door de phone nr die we net ingegeven hebben
            int rowsAffected = stmt.executeUpdate("UPDATE customers SET phone = '987-987-6325' WHERE customer_id = 5");
            System.out.println("Rows affected: " + rowsAffected);

            //or another way -> hier gaat de id 6 geupdate worden met de phone number die we net ingeven
           stmt.executeUpdate("UPDATE customers SET phone = '987-987-6325' WHERE customer_id = 6");
            System.out.println("Rows affected: " + rowsAffected);

        }catch (SQLException se){
            System.out.println(se.getMessage());
        }
    }
}
