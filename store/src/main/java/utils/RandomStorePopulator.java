package utils;
import com.github.javafaker.Faker;


public class RandomStorePopulator {
   private Faker faker = new Faker();
   public String getProductName(String nameOfCategory){
      switch(nameOfCategory){
         case "Bike":
            return faker.ancient().hero();
         case "Phones":
            return faker.ancient().hero();
         case "Milk":
            return faker.ancient().hero();
         default:
            return null;
      }
   }

   public int  getProductPrice() {
      return faker.number().randomDigit();
   }

   public int getProductRate(){
      return faker.number().randomDigit();
   }
}
