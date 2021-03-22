package service;

import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryService {
    private static final String SELECT_ALL_CATEGORY = "SELECT * FROM category;";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT categoryName FROM category WHERE id = ?";

    public String getName(int id) {
        Category category = null;
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnection.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int _id = rs.getInt("id");
                String categoryName = rs.getString("categoryName");
                category = new Category(_id, categoryName);
            }
        } catch (SQLException e) {
            DBConnection.printSQLException(e);
        }
        return category.getCategoryName();
    }
}
