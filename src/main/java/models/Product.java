package models;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Product {

    private String title;
    private Category category = Category.DRY_CURED;
    private double price = 0;
    private int amount = 0;
    private String description = "";
    private List<File> images = new ArrayList<File>();
    private Date prodauctionDate;

    public Product(String title) {
        this.title = title;
    }

    public String getTitle() {
        if (title == null) throw
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
