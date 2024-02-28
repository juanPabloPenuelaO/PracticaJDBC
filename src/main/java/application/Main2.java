package application;

import repository.DatabaseConnection;
import repository.Products;
import repository.Repository;
import repository.impl.ProductRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getInstance()) {
            Repository<Products> repository = new ProductRepositoryImpl();
            System.out.println("**** List products from database");
            repository.list().stream().forEach(System.out::println);
            System.out.println("**** Get by Id: 1");
            System.out.println(repository.byId(1L).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
