import dao.DAOImpl0;
import dao.IDAO;
import lombok.extern.java.Log;
import org.eclipse.jetty.server.Server;
import servlets.ImageCardsServlet;

import java.sql.*;

@Log
public class Main {


    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {

        final Connection connection = getPGConnection();
        final IDAO dao = new DAOImpl0(connection);

        Server server = new ServerBuilder(PORT)
                .addServlet(new ImageCardsServlet(), ImageCardsServlet.IMAGE_CARD_PATH)
                .build();

        server.start();
        log.info("The server has been started");
        server.join();
    }

    public static Connection getPGConnection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/delicatesov";
            String name = "pgdelicatesov";
            String pass = "as58key3024";

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
