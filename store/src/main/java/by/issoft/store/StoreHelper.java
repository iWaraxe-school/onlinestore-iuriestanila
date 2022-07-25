package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import org.reflections.Reflections;
import utils.RandomStorePopulator;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class StoreHelper {
    Store store;
    public StoreHelper(Store store){
        this.store=store;
    }
    public void populateTheStore(){
        RandomStorePopulator populator = new RandomStorePopulator();
        Set<Category> categorySet = getReflectionsInstances();

        for(Category category: categorySet) {
            for(int i=0; i<=3; i++){
                Product product = new Product(populator.getProductName(category.getName()),
                        populator.getProductRate(), populator.getProductPrice());
                category.addProductToCategory(product);
            }
            store.addCategory(category);
        }
    }
    public static Set<Category> getReflectionsInstances() {
        Set<Category> categorySet = new HashSet<Category>();

        Reflections reflections = new Reflections("by.issoft.domain.categories");
        Set<Class<? extends Category>> subtypes = reflections.getSubTypesOf(Category.class);
        for (Class<? extends Category> i : subtypes) {
            try {
                categorySet.add(i.getConstructor().newInstance());
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return categorySet;
    }
}
