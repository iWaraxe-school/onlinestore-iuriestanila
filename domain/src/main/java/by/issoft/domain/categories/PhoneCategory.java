package by.issoft.domain.categories;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.List;

public class PhoneCategory extends Category {
    private String name;

    public PhoneCategory(String name, List<Product> products) {
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
        return "\nPhoneCategory {name "+name+", products "+products+"}";
    }
}
