package com.soaresmessiasg130;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsingH2 {
  public static void main(String[] args) {
    var databaseUrl = "jdbc:h2:mem:";

    try (
      var conn = DriverManager.getConnection(databaseUrl);
      var statement = conn.createStatement();
      var resultSet = statement.executeQuery("SELECT 1 + 1")
    ){
      if (resultSet.next()) {
        System.out.println(resultSet.getInt(1));
      }
    } catch (SQLException ex) {
      var logger = Logger.getLogger(UsingH2.class.getName());

      logger.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}