package be.intecbrussel;

import java.sql.*;

public class SearchBeer {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root","root");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT Name, Alcohol FROM beers_db.beers LIMIT 10")){

            while (rs.next()) {
                String name = rs.getString("Name");
                float alcohol = rs.getFloat("Alcohol");

                System.out.println(name + " " + alcohol);
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}
