package config;

import dao.DAOImpl0;
import dao.IDAO;
import lombok.extern.java.Log;
import templater.PageGenerator;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Log
public class Context {
    private final Properties properties = new Properties();

    private Connection connection;
    private Crypto crypto;
    private final String SALT = "zMdB#T8712";
    private File propFile;


    public Context(String propAddress) throws IOException {
        propFile = new File(propAddress);
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

        crypto = new Crypto(SALT);
    }

    public int getServerPort(){
        return Integer.parseInt(properties.getProperty("serverPort"));
    }

    public IDAO getDao(){
        return new DAOImpl0(this.connection);
    }
    public PageGenerator getPageGenerator(){
        return PageGenerator.instance(this.properties.getProperty("templatesDir"));
    }

    public void setAdminSession(String session) throws IOException {
        properties.setProperty("adminSession", "" + crypto.cryptIt(session));
        properties.store(new FileOutputStream(propFile), null);
    }
    public void setAdminPass(String pass) throws IOException {
        properties.setProperty("adminPass", "" + crypto.cryptIt(pass));
        properties.store(new FileOutputStream(propFile), null);    }
    public boolean checkSession(String session){
        return properties.getProperty("adminSession", "no session").equals("" + crypto.cryptIt(session));
    }
    public boolean checkPass(String pass){
        return properties.getProperty("adminPass").equals("" + crypto.cryptIt(pass));
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

    private class Crypto {
        private String salt;

        private Crypto(String salt) {
            this.salt = salt;
        }

        private long cryptIt(String word){
            String saltWord = word + salt;
            long h1 = saltWord.hashCode();
            long h2= word.hashCode();
            long cryptWord = Math.abs(h1 * h2 / 100);
            return cryptWord;
        }
    }
}
