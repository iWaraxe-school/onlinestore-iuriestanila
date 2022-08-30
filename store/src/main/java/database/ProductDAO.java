package database;

import by.issoft.domain.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getProductsFromDatabase();
}
