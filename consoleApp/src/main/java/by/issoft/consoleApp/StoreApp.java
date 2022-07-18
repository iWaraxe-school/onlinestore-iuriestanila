package by.issoft.consoleApp;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import utils.RandomStorePopulator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class StoreApp {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        Store store = RandomStorePopulator.generateData();

        System.out.println(store);
    }
}
