package by.issoft.consoleApp;

import by.issoft.store.Store;
import by.issoft.store.StoreHelper;

import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) {

        Store store = new Store();
        StoreHelper storeHelper = new StoreHelper(store);
        storeHelper.populateTheStore();
        //System.out.println(store);

        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your command: ");
            String str = sc.nextLine();

            if(str.equals("quit")){
                break;
            }
            if(str.equals("top")){
                storeHelper.showTopXProducts(store,5);
            }
            if(str.equals("print")){
                System.out.println(store);
            }
            if(str.equals("sort")){
                storeHelper.sortProducts(store);
            }
        }
    }
}
