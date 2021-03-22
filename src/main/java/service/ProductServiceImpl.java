package service;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService {

    private static final String INSERT_PRODUCTS = "INSERT INTO product (productName, productPrice, productQuantity, productColor, categoryId) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product JOIN category ON product.categoryId = category.id;";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM product where id = ?;";
    private static final String SELECT_PRODUCT_BY_NAME = "SELECT * FROM product WHERE productName like ?;";
    private static final String UPDATE_PRODUCT_BY_ID = "UPDATE product SET productName = ?, productPrice = ?, productQuantity = ?, productColor = ?, categoryId = ? WHERE id = ?;";
    private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE id = ?;";

    @Override
    public void addNewProduct(Product p) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTS)) {
            preparedStatement.setString(1, p.getProductName());
            preparedStatement.setDouble(2, p.getProductPrice());
            preparedStatement.setInt(3, p.getProductQuantity());
            preparedStatement.setString(4, p.getProductColor());
            preparedStatement.setInt(5, p.getCategoryId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            DBConnection.printSQLException(e);
        }
    }

    public Product getProductByID(int id) {
        Product p = null;
        try (Connection connection = DBConnection.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int _id = rs.getInt("id");
                String productName = rs.getString("productName");
                double productPrice = rs.getDouble("productPrice");
                int productQuantity = rs.getInt("productQuantity");
                String productColor = rs.getString("productColor");
                int categoryId = rs.getInt("categoryId");
                p = new Product(_id, productName, productPrice, productQuantity, productColor, categoryId);
            }
        } catch (SQLException e) {
            DBConnection.printSQLException(e);
        }
        return p;
    }

    @Override
    public List<Product> searchProduct(String name) {
        List<Product> resultOutcome = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME);) {
            name = "%" + name + "%";
            preparedStatement.setString(1, name);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("productName");
                double productPrice = rs.getDouble("productPrice");
                int productQuantity = rs.getInt("productQuantity");
                String productColor = rs.getString("productColor");
                int categoryId = rs.getInt("categoryId");
                resultOutcome.add(new Product(id, productName, productPrice, productQuantity, productColor, categoryId));
            }
        } catch (SQLException e) {
            DBConnection.printSQLException(e);
        }
        return resultOutcome;
    }

    @Override
    public boolean updateProduct(Product p) {
        boolean rowUpdated = false;
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_BY_ID);) {
            statement.setString(1, p.getProductName());
            statement.setDouble(2, p.getProductPrice());
            statement.setInt(3, p.getProductQuantity());
            statement.setString(4, p.getProductColor());
            statement.setInt(5, p.getCategoryId());
            statement.setInt(6, p.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteProduct(int id) {
        boolean rowDeleted = false;
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_BY_ID);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    @Override
    public List<Product> showAllProducts() {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("productName");
                double productPrice = rs.getDouble("productPrice");
                int productQuantity = rs.getInt("productQuantity");
                String productColor = rs.getString("productColor");
                int categoryId = rs.getInt("categoryId");
                String categoryName = rs.getString("categoryName");
                Product p = new Product(id, productName, productPrice, productQuantity, productColor, categoryId);
                p.setCategoryName(categoryName);
                productList.add(p);
            }
        } catch (SQLException e) {
            DBConnection.printSQLException(e);
        }
        return productList;
    }
}
