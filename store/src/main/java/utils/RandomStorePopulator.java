package utils;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.categories.BikeCategory;
import by.issoft.domain.categories.MilkCategory;
import by.issoft.domain.categories.PhoneCategory;
import by.issoft.store.Store;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomStorePopulator {

   public static Store generateData(){
      Faker faker = new Faker();
      List<Category> categories = new ArrayList<Category>();

      BikeCategory mountainBike = new BikeCategory("MountainBike", Arrays.asList
              (new Product((faker.commerce().productName()),faker.number().randomDigit(),faker.commerce().price())));
     categories.add(mountainBike);

      BikeCategory cityBike = new BikeCategory("CityBike",Arrays.asList
             (new Product((faker.commerce().productName()),faker.number().randomDigit(),faker.commerce().price())));
       categories.add(cityBike);

      PhoneCategory smartphone = new PhoneCategory("Smartphone",Arrays.asList
              (new Product(faker.commerce().productName(), faker.number().randomDigit(), faker.commerce().price())));
      categories.add(smartphone);

      PhoneCategory simplePhone = new PhoneCategory("SimplePhone",Arrays.asList
              (new Product(faker.commerce().productName(), faker.number().randomDigit(), faker.commerce().price())));
      categories.add(simplePhone);

      MilkCategory standard = new MilkCategory("Standard",Arrays.asList
              (new Product(faker.commerce().productName(),faker.number().randomDigit(),faker.commerce().price())));
      categories.add(standard);

      MilkCategory select = new MilkCategory("Select",Arrays.asList
              (new Product(faker.commerce().productName(),faker.number().randomDigit(),faker.commerce().price())));
      categories.add(select);

      Store store = new Store(categories);

      return store;
   }
}
