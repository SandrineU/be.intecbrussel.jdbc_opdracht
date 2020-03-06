package be.intecbrussel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdates {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_store", "root", "root");
             Statement stmt = conn.createStatement()) {

            conn.setAutoCommit(false);

            stmt.addBatch("UPDATE customers SET city = 'BXL' WHERE customer_id = 1");
            stmt.addBatch("UPDATE customers SET phone='654-325-789' WHERE customer_id=2");

            stmt.executeBatch(); //geeft een int terug
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException se) {
            se.printStackTrace();
            System.out.println(se.getMessage());
        }
    }
}
