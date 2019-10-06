package config;

import lombok.extern.java.Log;
import service.Executor;
import templater.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    private StringBuilder adminSession;
    private String adminPass;


    public Context(String propAddress) throws IOException {
        propFile = new File(propAddress);
        crypto = new Crypto(SALT);
        try(FileReader reader = new FileReader(propFile)) {
            properties.load(reader);
            log.info("Properties is loaded");
        } catch (IOException e) {
            e.printStackTrace();
            log.warning("File isn't found");
        }

        try {
            String urlDB = properties.getProperty("urlDB");
            String nameDB = properties.getProperty("nameDB");
            String passDB = String.valueOf(crypto.cryptIt(properties.getProperty("passDB")));
            log.info("Pass is: " + passDB);

            this.connection = DriverManager.getConnection(urlDB, nameDB, passDB);
            log.info("Database was connected");
        } catch (SQLException e) {
            e.printStackTrace();
            log.warning("Database wasn't connected");
        }

        this.adminSession = new StringBuilder(properties.getProperty("adminSession"));
        this.adminPass = properties.getProperty("adminPass");
    }

    public int getServerPort(){
        return Integer.parseInt(properties.getProperty("serverPort"));
    }

    public PageGenerator getPageGenerator(){
        return PageGenerator.instance(this.properties.getProperty("templatesDir"));
    }

    public Executor getExecutor(){
        return new Executor(connection);
    }

    public void setAdminSession(String session) throws IOException {
        adminSession.append(crypto.cryptIt(session));
        properties.setProperty("adminSession", "" + adminSession.toString());
        properties.store(new FileOutputStream(propFile), null);
    }
    public void setAdminPass(String pass) throws IOException {
        properties.setProperty("adminPass", "" + crypto.cryptIt(pass));
        properties.store(new FileOutputStream(propFile), null);
    }

    public boolean checkSession(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("SESSIONID") &&
                        adminSession.toString().contains(String.valueOf(crypto.cryptIt(cookie.getValue())))){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkPass(String pass){
        return adminPass.equals("" + crypto.cryptIt(pass));
    }

    public String validateString(String string){
        string = string.replace("&", "&am;");
        string = string.replace("<", "&lt;");
        string = string.replace(">", "&gt;");
        return string;
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
