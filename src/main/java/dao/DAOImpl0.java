package dao;

import models.*;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class DAOImpl0 implements IDAO {
    private final Connection connection;

    public DAOImpl0(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Category> findCategories() {
        return null;
    }

    @Override
    public Category findCategoryById(int categoryId) {
        return null;
    }

    @Override
    public void addCategory(Category category) {

    }

    @Override
    public void updateCategory(Category category) {

    }

    @Override
    public List<Client> findClients() {
        return null;
    }

    @Override
    public List<Client> findClientsByName(String name) {
        return null;
    }

    @Override
    public List<Client> findClientsByPhone(int phone) {
        return null;
    }

    @Override
    public Client findClientById(int clientId) {
        return null;
    }

    @Override
    public void addClient(Client client) {

    }

    @Override
    public void updateClient(Client client) {

    }

    @Override
    public List<Comment> findComments() {
        return null;
    }

    @Override
    public List<Comment> findCommentsByProductId(int productId) {
        return null;
    }

    @Override
    public List<Comment> findCommentsByClientId(int clientId) {
        return null;
    }

    @Override
    public List<Comment> findCommentsByDate(Date date) {
        return null;
    }

    @Override
    public Comment findCommentById(int commentId) {
        return null;
    }

    @Override
    public void addComment(Comment comment) {

    }

    @Override
    public void updateComment(Comment comment) {

    }

    @Override
    public List<Order> findOrders() {
        return null;
    }

    @Override
    public List<Order> findOrdersByOrderDate(Date orderDate) {
        return null;
    }

    @Override
    public List<Order> findOrdersByCompleteDate(Date completeDate) {
        return null;
    }

    @Override
    public List<Order> findOrdersByClient(Client client) {
        return null;
    }
}
