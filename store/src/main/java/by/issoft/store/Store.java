package by.issoft.store;
import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class Store {
   List<Category> categories = new ArrayList<Category>();

    private Store(){
    }

    private static class createStoreInstance {
        private static final Store storeInstance = new Store();
    }

    public static Store getStoreInstance(){
        return createStoreInstance.storeInstance;
    }

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
                sb.append("\t"+product+"\n");
            }
        }
        return sb.toString();
    }
}
