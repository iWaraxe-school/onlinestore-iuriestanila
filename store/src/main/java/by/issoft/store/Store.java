package by.issoft.store;
import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class Store {
   List<Category> categories = new ArrayList<Category>();

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category){
        categories.add(category);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Store contents:\n ");
        for(Category category: getCategories()){
            sb.append(category.getName()+"\n");
            for(Product product: category.getProducts()){
                sb.append("\t"+product.getInfoProduct()+"\n");
            }
        }
        return sb.toString();
    }
}
