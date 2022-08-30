package by.issoft.domain;

public class Product {
    private int id;
    private String name;
    private int rate;
    private int price;
    private int categoryID;


    public Product(String name, int rate, int price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public Product(int id, int categoryID, String name, int rate, int price ) {
        this.id = id;
        this.categoryID=categoryID;
        this.name = name;
        this.rate = rate;
        this.price = price;

    }

    public int getCategoryID() {
        return categoryID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product: "+name+", rate: "+rate+", price: "+price;
    }

}