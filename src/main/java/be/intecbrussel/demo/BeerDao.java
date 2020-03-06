package be.intecbrussel.demo;

import java.sql.*;

public class BeerDao {

    private String url;
    private String username;
    private String password;

    public BeerDao(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }





    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }

    public Beer getBeerById(int id) throws BeerException {
        try(Connection connection = getConnection();    //bij prepStatement moet je niet eerst createStatement doen...
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Id, Name, Price, Stock, Alcohol FROM beers_db.beers WHERE Id =?")){
            //placeholder wordt voorgesteld door een '?' -> 1 omdat onze quesry met 1 begint en dan wat er ingevuld moet worden in deze geval 'id' die moet wel een int terug geven
            preparedStatement.setInt(1,id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                //aangezien we maar 1 resulaat moeten bekomen gaan we if gebruiken ipv while
                if (resultSet.next()){
                    Beer beer = new Beer();
                  //in vorige oef:  int id1 = resultSet.getInt("Id");
                    beer.setId(resultSet.getInt("Id"));
                    beer.setName(resultSet.getString("Name"));
                    beer.setPrice(resultSet.getFloat("Price"));
                    beer.setStock(resultSet.getInt("Stock"));
                    beer.setAlcohol(resultSet.getFloat("Alcohol"));
                   return beer;
                } else {
                    return null;
                }
            }
        }catch (SQLException se){
            System.out.println(se.getMessage());
            throw new BeerException("SOMETHING WENT WRONG, PLEASE TRY AGAIN LATER!");
        }
      }
}
