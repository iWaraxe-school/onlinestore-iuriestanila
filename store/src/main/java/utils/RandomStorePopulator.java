package utils;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.categories.BikeCategory;
import by.issoft.store.Store;
import com.github.javafaker.Faker;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class RandomStorePopulator {

   public static Store generateData() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
      Faker faker = new Faker();
      List<Category> categories = new ArrayList<Category>();

      //at line 23 occurs NoSuchMethodException.
      Class<BikeCategory> bikeclass = BikeCategory.class;
      Constructor constructor = bikeclass.getConstructor(String.class, Product.class);
      BikeCategory bike = (BikeCategory) constructor.newInstance("MountainBike", (new Product((faker.commerce().productName()),
              faker.number().randomDigit(), faker.commerce().price())));
      categories.add(bike);


      Store store = new Store(categories);

      return store;
   }
}
