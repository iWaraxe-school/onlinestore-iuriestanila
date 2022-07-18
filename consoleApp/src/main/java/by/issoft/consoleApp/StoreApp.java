package by.issoft.consoleApp;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import utils.RandomStorePopulator;


public class StoreApp {
    public static void main(String[] args) {

        Store store = RandomStorePopulator.generateData();

        System.out.println(store);
    }
}
