package models;

import java.io.File;
import java.util.List;

public class Product {
    private static int commonId;

    private final int productId;
    private String productTitle;
    private String productDescription;
    private int productAmount;
    private double price;
    private List<File> images;

    public Product(String productTitle) {
        commonId = getCommonId();
        this.productId = commonId++;
        this.productTitle = productTitle;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
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
}
