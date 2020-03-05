package be.intecbrussel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDemo {
    public static void main(String[] args) {

        String update1 = "UPDATE customers SET points = 10 WHERE customer_id =1";
        String update2 = "UPDATE customers SET points = 20 WHERE customer_id =2";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_store", "root", "root");
             Statement statement = connection.createStatement()) {
            // we willen de 2 updates doorgaan als 1 transactie oplossing: set Auto Commit op false, want we willen dat zelf doen
            connection.setAutoCommit(false);

            statement.executeUpdate(update1);
            statement.executeUpdate(update2);
            connection.commit();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}
