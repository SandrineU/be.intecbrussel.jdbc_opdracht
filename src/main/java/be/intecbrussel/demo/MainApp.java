package be.intecbrussel.demo;

public class MainApp {
    public static void main(String[] args) {

        BeerDao beerDao = new BeerDao("jdbc:mysql://localhost:3306/sql_store", "root", "root");
        try {
            //Er zijn twee manieren - first way
            Beer beer = beerDao.getBeerById(12);
            System.out.println(beer);
            //Er zijn twee manieren - second way
            System.out.println(beerDao.getBeerById(15));
        } catch (BeerException e) {
            e.printStackTrace();
        }
    }}
