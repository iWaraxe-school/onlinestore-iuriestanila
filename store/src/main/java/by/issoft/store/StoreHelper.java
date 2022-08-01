package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import org.reflections.Reflections;
import parsing.SortByProductName;
import parsing.SortByProductPrice;
import parsing.SortByProductRate;
import parsing.XmlParser;
import utils.RandomStorePopulator;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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

    public void showTopXProducts(Store store, int limit) {
        List<Product> allProducts = new ArrayList<Product>();

        for(Category category : store.getCategories()){
            for(Product product : category.getProducts()){
                allProducts.add(product);
            }
        }

        allProducts.stream().sorted(Comparator.comparing(Product::getPrice).
                reversed()).limit(limit).forEach(product -> System.out.println(product.getInfoProduct()));
    }

    public static void sortProducts(Store store) {
        Map<String, String> linkedHMapConfig = XmlParser.parse("C:\\Users\\IurieStanila\\IdeaProjects\\" +
                "onlinestore-iuriestanila\\store\\src\\main" +
                "\\resources\\config.xml");

        List<Product> productsForSorting = new ArrayList<>();

        for(Category category: store.getCategories()){
            for(Product product: category.getProducts()){
                productsForSorting.add(product);
            }
        }
        productsForSorting.sort(new SortByProductName());

        System.out.println("\nSorting by name:\n ");
        productsForSorting.stream().forEach(product -> System.out.println(product.getInfoProduct()));


        System.out.println("\nSorting by price:\n ");
        productsForSorting.sort(new SortByProductPrice());
        productsForSorting.stream().forEach(product -> System.out.println(product.getInfoProduct()));

        System.out.println("\nSorting By rate:\n");
        productsForSorting.sort(new SortByProductRate());
        productsForSorting.stream().forEach(product -> System.out.println(product.getInfoProduct()));

//        Comparator<Product> comparator = Comparator.comparing(Product::getName).
//                thenComparing(Product::getPrice).thenComparing(Product::getRate).reversed();

        // Collections.sort(store,comparator);
    }
}
