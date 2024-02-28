package repository.impl;
import repository.DatabaseConnection;
import repository.Products;
import repository.Repository;


import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements Repository<Products> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    private Products createProduct(ResultSet resultSet) throws SQLException {
        Products producto = new Products();
        producto.setId(resultSet.getInt("id"));
        producto.setName(resultSet.getString("name"));
        producto.setPrice(resultSet.getDouble("price"));
        producto.setRegistration_date(
                resultSet.getDate("registration_date") != null ?
                        resultSet.getDate("registration_date")
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime() :
                        null);
        return producto;
    }

    @Override
    public List<Products> list() {
        List<Products> productoList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from products")) {
            while (resultSet.next()) {
                Products product = createProduct(resultSet);
                productoList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productoList;
    }

    @Override
    public Products byId(Long id) {
        Products product = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM products WHERE id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = createProduct(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void save(Products products) {

    }

    @Override
    public void delete(Long id) {

    }

}