package by.issoft.consoleApp;

import by.issoft.store.Store;
import by.issoft.store.StoreHelper;

public class StoreApp {
    public static void main(String[] args) {

        Store store = new Store();
        StoreHelper storeHelper = new StoreHelper(store);
        storeHelper.populateTheStore();
        System.out.println(store);
    }
}
