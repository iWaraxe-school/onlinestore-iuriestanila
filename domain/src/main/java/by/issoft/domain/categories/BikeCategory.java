package by.issoft.domain.categories;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.List;

public class BikeCategory extends Category {
    private String name;

    public BikeCategory(String name,List<Product> products) {
        super(products);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nBikeCategory {name "+name+", products "+products+"}";
    }
}
