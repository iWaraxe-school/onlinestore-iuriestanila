package by.issoft.domain;

import java.util.List;

public class Category {
   protected List<Product> products;

   public Category(List<Product> products){
       this.products = products;
   }

    public List<Product> getProducts() {
        return products;
    }
}
