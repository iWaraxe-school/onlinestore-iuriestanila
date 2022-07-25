package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
   protected List<Product> products;

   public Category(String name){
       this.name = name;
       this.products = new ArrayList<Product>();
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
