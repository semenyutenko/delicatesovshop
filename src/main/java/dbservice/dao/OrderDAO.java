package dbservice.dao;

import dbservice.executor.Executor;
import models.Order;

import java.sql.Connection;
import java.util.ArrayList;

public class OrderDAO {
    private final Executor executor;

    public OrderDAO(Connection connection){
        this.executor = new Executor(connection);
    }

    public ArrayList<Order> getOrders(){
        ArrayList<Order> orders = new ArrayList<>();
        executor.execQuery("select * from orders", resultSet -> {
            Order order = new Order(resultSet.getDate("order_date"));
            order.setOrderId(resultSet.getInt("order_id"));
            order.setClient(getClientById(resultSet.getInt("client_id")));
        })
    }
}
