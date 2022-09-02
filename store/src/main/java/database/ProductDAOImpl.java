package database;

import by.issoft.domain.Product;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    Connection connection;
    public ProductDAOImpl(Connection connection){
        this.connection = connection;
    }

    @SneakyThrows
    public List<Product> getProductsFromDatabase() {
        final String retrieveProductsFromDB = "SELECT ID, Name, CategoryID, Rate, Price  FROM products";
        List<Product> productsFromDatabase = new ArrayList<>();

        Statement statement =  connection.createStatement();
        ResultSet resultSet = statement.executeQuery(retrieveProductsFromDB);

        while(resultSet.next()) {
            productsFromDatabase.add(new Product(resultSet.getInt("ID"),
                    resultSet.getInt("CategoryID"),
                    resultSet.getString("Name"),
                    resultSet.getInt("Rate"),
                    resultSet.getInt("Price")));
        }
        return productsFromDatabase;
    }
}
