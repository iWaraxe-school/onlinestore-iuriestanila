package database;

import by.issoft.domain.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getCategoriesFromDatabase();
}
