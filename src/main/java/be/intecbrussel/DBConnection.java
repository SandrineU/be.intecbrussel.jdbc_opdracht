package be.intecbrussel;

import java.sql.*;

public class DBConnection {
    public static void main(String[] args) {

        // hoe een link maken met

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_store", "root","root");
            //TYPE_SCROLL_INSENSITIVE cursor gaat van achter naar voor en van voor naar achter niet alleen in een richting
            //CONCUR_READ_ONLY waar je de gegevens niet kan updaten tov de ander methode
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery("SELECT products.product_id, products.name FROM sql_store.products");){
            // Hoef je niet bij te zetten -> connection.close();

            //als je de cursor na de laatste row/record zetten en die van achter naar voor wil laten lezen
            // dan moet je   while(rs.previous()) zetten ipv   while(rs.next())
            rs.afterLast();
            // onderstaande is nodig omdat onze cursor net voor row 1 staat en die info moeten we doorloopen
            while(rs.previous()){
                // Eerste manier:
                //int id = rs.getInt(1); '1' staat voor de kolom index, maar qua leesbaarheid is het niet TOP
                //String name = rs.getString("name"); 'name' staat hier voor de kolomnaam
                //System.out.println(id + " -> " + name);
                System.out.println(rs.getInt("product_id") + " -> " + rs.getString("name"));
            }

            System.out.println("Connecting with " + connection.getCatalog() + " database");
        } catch (SQLException se){
            se.printStackTrace();
            System.out.println(se.getMessage());
        }
    }
}
