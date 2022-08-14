package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import orders.Cleaner;
import orders.Order;
import org.reflections.Reflections;
import parsing.XmlParser;
import utils.RandomStorePopulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StoreHelper {
    Store store;
    Order order = Order.getOrderInstance();
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    public StoreHelper(Store store) {
        this.store = store;
    }
    public StoreHelper(){
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
                        productsToSort.stream().forEach(product -> System.out.println(product));
                        System.out.println("--------------------------------------------------------------");
                    } else {
                        productsToSort.sort(Comparator.comparing(Product::getName).reversed());
                        System.out.println("Sorted by name (desc): ");
                        productsToSort.stream().forEach(product -> System.out.println(product));
                        System.out.println("--------------------------------------------------------------");
                    }
                    break;
                case "price":
                    if (values.get(i).equals("asc")) {
                        productsToSort.sort(Comparator.comparing(Product::getPrice));
                        System.out.println("Sorted by price (asc): ");
                        productsToSort.stream().forEach(product -> System.out.println(product));
                        System.out.println("--------------------------------------------------------------");
                    } else {
                        productsToSort.sort(Comparator.comparing(Product::getPrice).reversed());
                        System.out.println("Sorted by price (desc): ");
                        productsToSort.stream().forEach(product -> System.out.println());
                        System.out.println("--------------------------------------------------------------");
                    }
                    break;
                case "rate":
                    if (values.get(i).equals("asc")) {
                        productsToSort.sort(Comparator.comparing(Product::getRate));
                        System.out.println("Sorted by rate (asc): ");
                        productsToSort.stream().forEach(product -> System.out.println(product));
                        System.out.println("--------------------------------------------------------------");
                    } else {
                        productsToSort.sort(Comparator.comparing(Product::getRate).reversed());
                        System.out.println("Sorted by rate (desc): ");
                        productsToSort.stream().forEach(product -> System.out.println(product));
                        System.out.println("--------------------------------------------------------------");
                    }
                    break;
            }
        }

        System.out.println("\nAll the sorted products:");
        productsToSort.stream().forEach(product -> System.out.println(product));
    }


    public void createOrder(String orderedProduct){
        Product foundOrderedProduct = findOrderedProduct(orderedProduct);
        Random random = new Random();

        executorService.execute(()-> {
            // dupa asta de scos
            System.out.println(Thread.currentThread().getName());

            final int randomTime = random.nextInt(31 - 1) + 1;

            try {
                TimeUnit.SECONDS.sleep(randomTime);
                CopyOnWriteArrayList<Product> purchasedProducts = order.getPurchasedProducts();
                purchasedProducts.add(foundOrderedProduct);

                System.out.println("Purchased products");
                printPurchasedProducts(purchasedProducts);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void shutDownExecutor(){
        executorService.shutdown();
    }

    public Product findOrderedProduct(String orderedProduct) {
        Store store = Store.getStoreInstance();
        Product foundProduct = null;

        for(Category category : store.getCategories()){
            for(Product product : category.getProducts()){
                System.out.println(product);
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

    public void cleanPurchasedProducts(){
        Timer timer = new Timer();
        timer.schedule(new Cleaner(), 2000);
    }

    public void storeInteraction(){
        StoreHelper storeHelper = new StoreHelper();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Boolean flag = true;
        while (flag){
            System.out.println("\nEnter the command sort, top, order or quit: ");
            try {
                String command = reader.readLine();

                switch (command){
                    case "sort":
                        storeHelper.sortProducts(store);
                        break;
                    case "top":
                        storeHelper.showTop5Products(store);
                        break;
                    case "order":
                        System.out.println("Which product do you want to order?: ");
                        String orderedProduct = reader.readLine();
                        storeHelper.createOrder(orderedProduct);
                    case "quit":
                        flag = false;
                        executorService.shutdown();
                        break;
                    default:
                        System.out.println("The entered command does not exist.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
