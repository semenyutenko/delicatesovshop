package models;

import exceptions.DSNullPointedException;
import java.util.ArrayList;
import java.util.List;

public class Product {

    private int productId;
    private String title;
    private String category;
    private double price = 0;
    private double amount = 0;
    private String description = "";
    private List<String> images;

    public Product(String title) {
        this.title = title;
    }

    public String getTitle() throws DSNullPointedException {
        if(title == null) throw new DSNullPointedException("title");
        return title;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        if(images == null){
            images = new ArrayList<String>();
        }
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void addImage(String image){
        if (images == null){
            images = new ArrayList<String>();
        }
        images.add(image);
    }
}
