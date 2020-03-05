package be.intecbrussel;

import java.sql.*;

public class InsertData {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_store", "root", "root");
             Statement statement = connection.createStatement()){
           // int rowsAffected = statement.executeUpdate("INSERT INTO customers(first_name, last_name, birth_date, phone, address, city, state,points) " +
             //       "VALUES ('john','doe','1991-06-14','98621-46522','addresss','city','se', '250')");
//state aanvaard alleen 2 karakters DUS NIET MEER DAN 2 LETTERS INGEVEN
            // Of op een andere manier zonder de kolomen naam te geven - > gewoon DEFAULT toevoegen bij id name en ok bij points als er niets ingevuld is
            int rowsAffected =statement.executeUpdate("INSERT INTO customers "+
                   "VALUES (DEFAULT,'john','doe','1991-06-14','98621-46522','addresss','city','st','250')",
                    Statement.RETURN_GENERATED_KEYS);
            try (ResultSet rs = statement.getGeneratedKeys()){
                if (rs.next()){
                    int id = rs.getInt(1); //kolomnaam kunnen we niet ingeven dus we gaan gewoon de index geven van de kolom '1'
                    System.out.println(id);
                }
            }
            System.out.println("Affected rows: " + rowsAffected);

        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }
    }
}
