package application;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) {
  String url= "jdbc:mysql://localhost:3306/test";
  String user= "root";
  String password = "admin";
  try (Connection conn= DriverManager.getConnection(url, user, password);
       Statement statement = conn.createStatement();
       ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
  ){
      while(resultSet.next()){
          System.out.println(resultSet.getInt("id"));
          System.out.println("|");
          System.out.println(resultSet.getString("name"));
          System.out.println("|");
          System.out.println(resultSet.getDouble("price"));
          System.out.println("|");
          System.out.println(resultSet.getDate("registration_date"));
      }
  } catch (SQLException e){
      e.printStackTrace();
  }
    }
}
