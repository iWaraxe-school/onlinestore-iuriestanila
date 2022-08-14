package orders;

import by.issoft.store.StoreHelper;

import java.util.TimerTask;

public class Cleaner extends TimerTask {
    StoreHelper storeHelper = new StoreHelper();
    @Override
    public void run() {
       storeHelper.cleanPurchasedProducts();
    }
}
