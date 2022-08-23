package orders;

import by.issoft.domain.Product;
import by.issoft.store.StoreHelper;

import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cleaner extends TimerTask {
    private CopyOnWriteArrayList<Product> purchasedProducts;

    public Cleaner(CopyOnWriteArrayList<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    @Override
    public void run() {
        purchasedProducts.clear();
        System.out.println("The list of purchased products have been cleared.");
    }
}
