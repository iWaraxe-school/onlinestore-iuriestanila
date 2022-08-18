package orders;

import by.issoft.domain.Product;
import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class Order implements Runnable {
    private  CopyOnWriteArrayList<Product> purchasedProducts;
    private Product foundOrderedProduct;

    public Order(Product foundOrderedProduct,CopyOnWriteArrayList<Product> purchasedProducts) {
        this.foundOrderedProduct = foundOrderedProduct;
        this.purchasedProducts = purchasedProducts;
    }

    @SneakyThrows
    @Override
    public void run() {
        Random random = new Random();
        System.out.println("Executing task 1: " + Thread.currentThread().getName());
        final int randomTime = random.nextInt(2) + 1; //3

        TimeUnit.SECONDS.sleep(randomTime);
        System.out.println("After maximum 3 seconds the product will be put in a list of purchased products.");
        purchasedProducts.add(foundOrderedProduct);

        System.out.println("\nDear customer you have purchased following products:");
        purchasedProducts.stream().forEach(e -> System.out.println(e));
    }
}
