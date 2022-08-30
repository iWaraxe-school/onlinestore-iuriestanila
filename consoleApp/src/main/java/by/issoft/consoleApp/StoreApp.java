package by.issoft.consoleApp;

import by.issoft.store.Store;
import by.issoft.store.StoreHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) {
        StoreHelper storeHelper = new StoreHelper();
        storeHelper.interactionDatabaseOrReflections();
        storeHelper.storeInteractionSortOrderTop();
    }
}
