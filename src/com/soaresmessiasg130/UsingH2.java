package com.soaresmessiasg130;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsingH2 {
  public static void main(String[] args) {
    testConnection();

    getAvailableCars();
  }

  private static void testConnection() {
    System.out.println(">>> Testing Connection");

    var databaseUrl = "jdbc:h2:mem:";

    try (
      var conn = DriverManager.getConnection(databaseUrl);
      var statement = conn.createStatement();
      var resultSet = statement.executeQuery("SELECT 1 + 2")
    ){
      if (resultSet.next()) {
        System.out.println(">>> Connection OK!!!");
        System.out.printf(">>> Result test: %d\n", resultSet.getInt(1));
      }
    } catch (SQLException ex) {
      var logger = Logger.getLogger(UsingH2.class.getName());

      logger.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

  private static void getAvailableCars() {
    System.out.println(">>> Get available cars");

    var databaseUrl = "jdbc:h2:tcp://192.168.0.103:9092/~/tmp/h2dbs/testdb";
    
    var user = "";

    var password = "";

    var query = "SELECT * FROM cars";

    try (
      var conn = DriverManager.getConnection(databaseUrl, user, password);
      var statement = conn.createStatement();
      var result = statement.executeQuery(query)
    ) {
      System.out.println(">>> Result:");

      System.out.println("ID | NAME | PRICE");

      while (result.next()) {
        System.out.printf(
          "%d %s %d%n\n", 
          result.getInt(1), 
          result.getString(2),
          result.getInt(3)
        );
      }
    } catch (SQLException ex) {
      var logger = Logger.getLogger(UsingH2.class.getName());

      logger.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}