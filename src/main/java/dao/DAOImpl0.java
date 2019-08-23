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

    public List<Account> findAccounts() {
        return null;
    }

    public List<Account> findAccountsByClient(int clientId) {
        return null;
    }

    public Account findAccountByUserName(String userName) {
        return null;
    }

    public void addAccount(Account account) {

    }

    public void updateAccount(Account account) {

    }

    public List<Category> findCategories() {
        return null;
    }

    public Category findCategoryById(int categoryId) {
        return null;
    }

    public void addCategory(Category category) {

    }

    public void updateCategory(Category category) {

    }

    public List<Client> findClients() {
        return null;
    }

    public List<Client> findClientsByName(String name) {
        return null;
    }

    public List<Client> findClientsByPhone(int phone) {
        return null;
    }

    public Client findClientById(int clientId) {
        return null;
    }

    public void addClient(Client client) {

    }

    public void updateClient(Client client) {

    }

    public List<Comment> findComments() {
        return null;
    }

    public List<Comment> findCommentsByProductId(int productId) {
        return null;
    }

    public List<Comment> findCommentsByClientId(int clientId) {
        return null;
    }

    public List<Comment> findCommentsByDate(Date date) {
        return null;
    }

    public Comment findCommentById(int commentId) {
        return null;
    }

    public void addComment(Comment comment) {

    }

    public void updateComment(Comment comment) {

    }

    public List<Order> findOrders() {
        return null;
    }

    public List<Order> findOrdersByOrderDate(Date orderDate) {
        return null;
    }

    public List<Order> findOrdersByCompleteDate(Date completeDate) {
        return null;
    }

    public List<Order> findOrdersBy() {
        return null;
    }
}
