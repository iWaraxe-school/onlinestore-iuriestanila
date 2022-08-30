package database;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DatabaseHelper {
    Product product;

    public Store loadData() {
        Store store = Store.getStoreInstance();

        try (Connection connection = DriverManager.getConnection(Configs.urlDB, Configs.userDB, Configs.passDB)) {

            ProductDAOImpl productDAOImpl = new ProductDAOImpl(connection);
            final List<Product> productsFromDB = productDAOImpl.getProductsFromDatabase();

            CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl(connection);
            final List<Category> categoriesFromDB = categoryDAOImpl.getCategoriesFromDatabase();

            for (Category category : categoriesFromDB) {
                for (Product product : productsFromDB) {
                    if (product.getCategoryID() == category.getId()) {
                        category.addProductToCategory(product);
                    }
                }
                store.addCategory(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return store;
    }
}
