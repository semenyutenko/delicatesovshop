package models;

import exceptions.DSNullPointedException;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Product {

    private String title;
    private Category category = Category.UNDEFINED;
    private double price = 0;
    private int amount = 0;
    private String description = "";
    private List<File> images;
    private Date productionDate;

    public Product(String title) {
        this.title = title;
    }

    public String getTitle() throws DSNullPointedException {
        if(title == null) throw new DSNullPointedException("title");
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<File> getImages() {
        if(images == null){
            images = new ArrayList<File>();
        }
        return images;
    }

    public void setImages(List<File> images) {
        this.images = images;
    }

    public void addImage(File image){
        if (images == null){
            images = new ArrayList<File>();
        }
        images.add(image);
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public enum Category{
        DRY_CURED("сыровяленые"), BOILED("варёные"), UNDEFINED ("неопределенно");

        private String name;

        Category(String name){
            this.name = name;
        }

        public String getName(){return name;}
    }
}
