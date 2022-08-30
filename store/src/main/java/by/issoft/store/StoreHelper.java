package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import com.sun.org.apache.xpath.internal.operations.Or;
import database.DatabaseHelper;
import lombok.SneakyThrows;
import orders.Cleaner;
import orders.Order;
import org.reflections.Reflections;
import parsing.XmlParser;
import utils.RandomStorePopulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StoreHelper {
    Store store;
    Order order;
    Connection connection;
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    private CopyOnWriteArrayList<Product> purchasedProducts;

    public StoreHelper(){
    }
    public StoreHelper(Store store){
    }

    public void populateTheStoreReflections() {
        store = Store.getStoreInstance();

        RandomStorePopulator populator = new RandomStorePopulator();
        Set<Category> categorySet = getReflectionsInstances();

        for (Category category : categorySet) {
            for (int i = 0; i <= 3; i++) {
                Product product = new Product(
                        populator.getProductName(category.getName()),
                        populator.getProductRate(),
                        populator.getProductPrice());
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

    public void showTop5Products(Store store) {
        List<Product> allProducts = new ArrayList<Product>();

        for (Category category : store.getCategories()) {
            for (Product product : category.getProducts()) {
                allProducts.add(product);
            }
        }
        allProducts.stream().sorted(Comparator.comparing(Product::getPrice).
                reversed()).limit(5).forEach(product -> System.out.println(product));
    }

    public static void sortProducts(Store store) {
        Map<String, String> mapConfig = XmlParser.parse("C:\\Users\\IurieStanila\\IdeaProjects\\" +
                "onlinestore-iuriestanila\\store\\src\\main" +
                "\\java\\resources\\config.xml");

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
                    } else {
                        productsToSort.sort(Comparator.comparing(Product::getName).reversed());
                        System.out.println("Sorted by name (desc): ");
                    }
                    break;
                case "price":
                    if (values.get(i).equals("asc")) {
                        productsToSort.sort(Comparator.comparing(Product::getPrice));
                        System.out.println("Sorted by price (asc): ");
                    } else {
                        productsToSort.sort(Comparator.comparing(Product::getPrice).reversed());
                        System.out.println("Sorted by price (desc): ");
                    }
                    break;
                case "rate":
                    if (values.get(i).equals("asc")) {
                        productsToSort.sort(Comparator.comparing(Product::getRate));
                        System.out.println("Sorted by rate (asc): ");
                    } else {
                        productsToSort.sort(Comparator.comparing(Product::getRate).reversed());
                        System.out.println("Sorted by rate (desc): ");
                    }
                    break;
            }
            productsToSort.stream().forEach(product -> System.out.println(product));
            System.out.println("--------------------------------------------------------------\n");
        }

        System.out.println("\nAll the sorted products:");
        productsToSort.stream().forEach(product -> System.out.println(product));
    }

    public void createOrder(String orderedProduct){
        Product foundOrderedProduct = findOrderedProduct(orderedProduct);
        Order order = new Order(foundOrderedProduct, purchasedProducts);

        executorService.submit(order);
    }

    public void shutDownExecutor(){
        executorService.shutdown();
    }

    public Product findOrderedProduct(String orderedProduct) {
        Store store = Store.getStoreInstance();
        Product foundProduct = null;

        for(Category category : store.getCategories()){
            for(Product product : category.getProducts()){
                if(product.getName().equals(orderedProduct)){
                    foundProduct = product;
                }
            }
        }
        return foundProduct;
    }

    public void printPurchasedProducts(CopyOnWriteArrayList<Product> purchasedProducts){
        purchasedProducts.stream().forEach(e-> System.out.println(e));
    }

    @SneakyThrows
    public void storeInteractionSortOrderTop(){
        List<Product> products = new ArrayList<>();
        purchasedProducts = new CopyOnWriteArrayList<>();

        Cleaner cleaner = new Cleaner(purchasedProducts);

        Timer timer = new Timer();
        timer.schedule(cleaner, 120000,120000);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (Category category : store.getCategories()) {
            for (Product product : category.getProducts()) {
                products.add(product);
            }
        }

        Boolean flag = true;
        while (flag){
            System.out.println("\n"+"Dear customer enter the command sort, top, order or quit: ");

            String command = reader.readLine();

            switch (command){
                case "sort":
                    sortProducts(store);
                    break;
                case "top":
                    showTop5Products(store);
                    break;
                case "order":
                    boolean flag2 = true;
                    while(flag2) {
                        System.out.println("Which product do you want to order from " +
                                "the following products of the Store? " +
                                "For quitting the order please enter stop.");
                        System.out.println(store);
                        String orderedProduct = reader.readLine();
                        if(products.stream().anyMatch(p -> p.getName().equals(orderedProduct))){
                            createOrder(orderedProduct);
                        }
                        else if(orderedProduct.equalsIgnoreCase("stop")) {
                            flag2 = false;
                            executorService.shutdown();
                        }
                    }
                case "quit":
                    flag = false;
                    executorService.shutdown();
                    timer.cancel();
                    break;
                default:
                    System.out.println("The entered command does not exist.");
            }
        }
    }

    @SneakyThrows
    public void interactionDatabaseOrReflections(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Boolean flag = true;
        System.out.println("\n"+"Loading data trough reflections or from database? " +
                "Please enter reflections or database: ");

        String command = reader.readLine();
        switch (command){
            case "reflections":
                populateTheStoreReflections();
                break;
            case "database":
                populateTheStoreDataBase();
                System.out.println("This is the store from the database:"+store);
                break;
            default:
                System.out.println("The entered command does not exist.");
        }
    }
    private void populateTheStoreDataBase() {
        store = new DatabaseHelper().loadData();
    }
}