package config;

import dao.DAOImpl0;
import dao.IDAO;
import lombok.extern.java.Log;
import templater.PageGenerator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Log
public class Context {
    private final Properties properties = new Properties();

    private Connection connection;


    public Context(String propAddress){
        File propFile = new File(propAddress);
        try {
            properties.load(new FileReader(propFile));
            log.info("Properties is loaded");
        } catch (IOException e) {
            e.printStackTrace();
            log.warning("File isn't found");
        }

        try {
            String urlDB = properties.getProperty("urlDB");
            String nameDB = properties.getProperty("nameDB");
            String passDB = properties.getProperty("passDB");

            this.connection = DriverManager.getConnection(urlDB, nameDB, passDB);
            log.info("Database was connected");
        } catch (SQLException e) {
            e.printStackTrace();
            log.warning("Database wasn't connected");
        }
    }

    public int getServerPort(){
        return Integer.parseInt(properties.getProperty("serverPort"));
    }

    public IDAO getDao(){
        return new DAOImpl0(this.connection);
    }
    public String getAccessToken(){return this.properties.getProperty("accessToken");}
    public PageGenerator getPageGenerator(){
        return PageGenerator.instance(this.properties.getProperty("templatesDir"));
    }

    public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
