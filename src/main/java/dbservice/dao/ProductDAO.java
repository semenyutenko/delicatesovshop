package dbservice.dao;

import dbservice.executor.Executor;
import lombok.extern.java.Log;
import models.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@Log
public class ProductDAO {
    private final Executor executor;

    public ProductDAO(Connection connection){
        this.executor = new Executor(connection);
    }

    public ArrayList<Product> getProducts(){
        try {
            ArrayList<Product> products = new ArrayList<>();
            executor.execQuery("select * from products", resultSet -> {
                while (resultSet.next()){
                    Product product = new Product(resultSet.getString("title"));
                    product.setProductId(resultSet.getInt("product_id"));
                    product.setCategory(getCategoryById(resultSet.getInt("category_id")));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setAmount(resultSet.getDouble("amount"));
                    product.setDescription(resultSet.getString("description_product"));
                    ArrayList<String> images = getImagesById(resultSet.getInt("product_id"));
                    product.setImages(images);
                    products.add(product);
                }
                return products;
            });
        } catch (SQLException e) {
            log.warning("list of products hasn't been received");
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<String> getImagesById(int productId) {
        try {
            ArrayList<String> images = new ArrayList<>();
            executor.execQuery("select location from images where product_id = " + productId,
                    resultSet -> {
                while (resultSet.next()){
                    images.add(resultSet.getString("location"));
                }
                return images;
                    });
        } catch (SQLException e) {
            log.warning("the list of images hasn't been received");
            e.printStackTrace();
        }
        return null;
    }

    private String getCategoryById(int categoryId){
        try {
            executor.execQuery("select category_title from categories" +
                    " where category_id = " + categoryId, resultSet -> {
                resultSet.next();
                return resultSet.getString("category_title");
            });
        } catch (SQLException e) {
            log.warning("the list of categories hasn't been received");
            e.printStackTrace();
        }
        return null;
    }
}
