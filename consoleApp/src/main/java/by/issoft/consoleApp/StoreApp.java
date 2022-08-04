package by.issoft.consoleApp;

import by.issoft.store.Store;
import by.issoft.store.StoreHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) {
        Store store = new Store();
        StoreHelper storeHelper = new StoreHelper(store);
        storeHelper.populateTheStore();
        //System.out.println(store);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Boolean flag = true;

        while (flag){
            System.out.println("\nEnter the command sort, top or quit: ");
            try {
                String command = reader.readLine();

                switch (command){
                    case "sort":
                        storeHelper.sortProducts(store);
                        break;
                    case "top":
                        storeHelper.showTopXProducts(store,5);
                        break;
                    case "quit":
                        flag = false;
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
