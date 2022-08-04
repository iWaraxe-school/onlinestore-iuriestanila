package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import org.reflections.Reflections;
import parsing.XmlParser;
import utils.RandomStorePopulator;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class StoreHelper {
    Store store;

    public StoreHelper(Store store) {
        this.store = store;
    }

    public void populateTheStore() {
        RandomStorePopulator populator = new RandomStorePopulator();
        Set<Category> categorySet = getReflectionsInstances();

        for (Category category : categorySet) {
            for (int i = 0; i <= 3; i++) {
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

        for (Category category : store.getCategories()) {
            for (Product product : category.getProducts()) {
                allProducts.add(product);
            }
        }
        allProducts.stream().sorted(Comparator.comparing(Product::getPrice).
                reversed()).limit(limit).forEach(product -> System.out.println(product.getInfoProduct()));
    }

    public static void sortProducts(Store store) {
        Map<String, String> mapConfig = XmlParser.parse("C:\\Users\\IurieStanila\\IdeaProjects\\" +
                "onlinestore-iuriestanila\\store\\src\\main" +
                "\\resources\\config.xml");

        List<Product> productsToSort = new ArrayList<Product>();

        for (Category category : store.getCategories()) {
            for (Product product : category.getProducts()) {
                productsToSort.add(product);
            }
        }

        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        System.out.println("Content of config.xml: " + mapConfig + "\n");
        for (Map.Entry<String, String> entry : mapConfig.entrySet()) {
            keys.add(entry.getKey());
            values.add(entry.getValue());
        }

        for (int i = mapConfig.size() - 1; i >= 0; i--) {
            switch (keys.get(i)) {
                case "name":
                    if (values.get(i).equals("asc")) {
                        productsToSort.sort(Comparator.comparing(Product::getName));
                        System.out.println("Sorted by name (asc): ");
                        productsToSort.stream().forEach(product -> System.out.println(product.getInfoProduct()));
                        System.out.println("--------------------------------------------------------------");
                    } else {
                        productsToSort.sort(Comparator.comparing(Product::getName).reversed());
                        System.out.println("Sorted by name (desc): ");
                        productsToSort.stream().forEach(product -> System.out.println(product.getInfoProduct()));
                        System.out.println("--------------------------------------------------------------");
                    }
                    break;
                case "price":
                    if (values.get(i).equals("asc")) {
                        productsToSort.sort(Comparator.comparing(Product::getPrice));
                        System.out.println("Sorted by price (asc): ");
                        productsToSort.stream().forEach(product -> System.out.println(product.getInfoProduct()));
                        System.out.println("--------------------------------------------------------------");
                    } else {
                        productsToSort.sort(Comparator.comparing(Product::getPrice).reversed());
                        System.out.println("Sorted by price (desc): ");
                        productsToSort.stream().forEach(product -> System.out.println(product.getInfoProduct()));
                        System.out.println("--------------------------------------------------------------");
                    }
                    break;
                case "rate":
                    if (values.get(i).equals("asc")) {
                        productsToSort.sort(Comparator.comparing(Product::getRate));
                        System.out.println("Sorted by rate (asc): ");
                        productsToSort.stream().forEach(product -> System.out.println(product.getInfoProduct()));
                        System.out.println("--------------------------------------------------------------");
                    } else {
                        productsToSort.sort(Comparator.comparing(Product::getRate).reversed());
                        System.out.println("Sorted by rate (desc): ");
                        productsToSort.stream().forEach(product -> System.out.println(product.getInfoProduct()));
                        System.out.println("--------------------------------------------------------------");
                    }
                    break;
            }
        }

        System.out.println("\nAll the sorted products:");
        productsToSort.stream().forEach(product -> System.out.println(product.getInfoProduct()));
    }
}
