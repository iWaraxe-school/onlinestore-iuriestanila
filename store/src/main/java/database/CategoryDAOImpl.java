package database;

import by.issoft.domain.Category;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    Connection connection;

    public CategoryDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    @SneakyThrows
    public List<Category> getCategoriesFromDatabase() {
        final String retrieveCategoriesFromDB = "SELECT ID, Name FROM categories";

        List<Category> categoriesFromDatabase = new ArrayList<>();

        Statement statement =  connection.createStatement();
        ResultSet resultSet = statement.executeQuery(retrieveCategoriesFromDB);

        while(resultSet.next()) {
            categoriesFromDatabase.add(new Category(
                    resultSet.getString("Name"),
                    resultSet.getInt("ID")));
        }
        return categoriesFromDatabase;
    }
}
