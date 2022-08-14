package orders;

import by.issoft.domain.Product;

import java.util.concurrent.CopyOnWriteArrayList;

public class Order {
    private CopyOnWriteArrayList<Product> products = new CopyOnWriteArrayList<>();
    private  CopyOnWriteArrayList<Product> purchasedProducts = new CopyOnWriteArrayList<>();
    private Order(){
    }

    private static class createOrderInstance {
        private static final Order OrderInstance = new Order();
    }

    public static Order getOrderInstance(){
        return Order.createOrderInstance.OrderInstance;
    }

    public CopyOnWriteArrayList<Product> getProducts() {
        return products;
    }

    public CopyOnWriteArrayList<Product> getPurchasedProducts() {
        return purchasedProducts;
    }
}
