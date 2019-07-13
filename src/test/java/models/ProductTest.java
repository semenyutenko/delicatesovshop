package models;

import static org.junit.Assert.*;

public class ProductTest {

    @org.junit.Test
    public void getTitle() {
        String inputValue = "Ham";
        String expectedValue = inputValue;
        Product product = new Product(inputValue);
        assertEquals(expectedValue, product.getTitle());
    }

    @org.junit.Test
    public void setTitle() {
        String inputValue = "Ham";
        String expectedValue = inputValue;
        Product product = new Product("Сhicken");
        product.setTitle(inputValue);
        assertEquals(expectedValue, product.getTitle());
    }

    @org.junit.Test
    public void getCategory() {
    }

    @org.junit.Test
    public void setCategory() {
    }

    @org.junit.Test
    public void getDescription() {
    }

    @org.junit.Test
    public void setDescription() {
    }

    @org.junit.Test
    public void getAmount() {
    }

    @org.junit.Test
    public void setAmount() {
    }

    @org.junit.Test
    public void setPrice() {
    }

    @org.junit.Test
    public void getPrice() {
    }

    @org.junit.Test
    public void getImages() {
    }

    @org.junit.Test
    public void setImages() {
    }
}