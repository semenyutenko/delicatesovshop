package dbservice;

import exceptions.DSexception;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {

    private final Connection connection;

    public DBService(){
        this.connection = getPostgreSQLConnection();
    }

    public void printConnectionInfo(){
        try{
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static Connection getPostgreSQLConnection() {
        try {
            final String url = "jdbc:postgresql://localhost:5432/delicatesov";
            final String name = "pgdelicatesov";
            final String pass = "as58key3024";

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            System.out.println("doesn't work");
            e.printStackTrace();
        }
        return null;
    }
}
