package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private int id;
    protected List<Product> products;

    public Category(String name, int id){
        this.name = name;
        this.id = id;
        this.products = new ArrayList<Product>();
    }
    public Category(String name){
        this.name = name;
        this.products = new ArrayList<Product>();
    }

    public int getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }
    public void addProductToCategory(Product product){
        products.add(product);
    }
    public String getInfoCategory(){
        return getName()+": "+getProducts();
    }
}
