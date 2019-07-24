package models;

import java.util.Date;

public class Order {

    private Date orderDate;
    private Date compliteDate;
    private Client client;
    private Product product;
    private double amount = 0;
    private String comment = "";

    public Order(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getCompliteDate() {
        return compliteDate;
    }

    public void setCompliteDate(Date compliteDate) {
        this.compliteDate = compliteDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
