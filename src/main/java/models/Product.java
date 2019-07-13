package models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private static int commonId;

    private final int productId;
    private String title;
    private Category category = Category.DRY_CURED;
    private double price = 0;
    private int amount = 0;
    private String description = "";
    private List<File> images = new ArrayList<File>();

    public Product(String title) {
        commonId = getCommonId();
        this.productId = commonId++;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public List<File> getImages() {
        return images;
    }

    public void setImages(List<File> images) {
        this.images = images;
    }

    private static int getCommonId() {
        return 0;
        //TODO написать геттер на статический общий id для продукта;
    }

    public enum Category{
        DRY_CURED("сыровяленые"), BOILED("варёные");

        public String name;

        Category(String name){
            this.name = name;
        }

        public String getName(){return name;}
    }
}
