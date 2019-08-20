package dao;

import models.*;

import java.util.Date;
import java.util.List;

public interface IDAO {

    List<Account> findAccounts();
    List<Account> findAccountsByClient(int clientId);
    Account findAccountByUserName(String userName);
    void addAccount(Account account);
    void updateAccount(Account account);

    List<Category> findCategories();
    Category findCategoryById(int categoryId);
    void addCategory(Category category);
    void updateCategory(Category category);

    List<Client> findClients();
    List<Client> findClientsByName(String name);
    List<Client> findClientsByPhone(int phone);
    Client findClientById(int clientId);
    void addClient(Client client);
    void updateClient(Client client);

    List<Comment> findComments();
    List<Comment> findCommentsByProductId(int productId);
    List<Comment> findCommentsByClientId(int clientId);
    List<Comment> findCommentsByDate(Date date);
    Comment findCommentById(int commentId);
    void addComment(Comment comment);
    void updateComment(Comment comment);

    List<Order> findOrders();
    List<Order> findOrdersByOrderDate(Date orderDate);
    List<Order> findOrdersByCompleteDate(Date completeDate);
    List<Order> findOrdersBy






}
