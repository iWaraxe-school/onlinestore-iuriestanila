package by.issoft.store;
import by.issoft.domain.Category;

import java.util.ArrayList;
import java.util.List;
//Store - class that should handle category list
public class Store {
   List<Category> categories = new ArrayList<Category>();

   public Store(List<Category> categories){
       this.categories=categories;
   }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
       return "Store {categories "+categories+" }";
    }
}
