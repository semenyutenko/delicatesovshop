package dao;

import java.sql.Connection;

public class DAOImpl0 implements IDAO {
    private final Connection connection;

    public DAOImpl0(Connection connection){
        this.connection = connection;
    }
}
