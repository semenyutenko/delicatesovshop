package main;

import config.Context;
import lombok.extern.java.Log;
import org.eclipse.jetty.server.Server;
import service.ServerBuilder;
import servlets.AdminAccessServlet;
import servlets.ImageCardsServlet;
import servlets.NewClientServlet;

@Log
public class Main {

    private static int PORT;

    public static void main(String[] args) throws Exception {

        Context context = new Context(args[0]);
        PORT = context.getServerPort();

        Server server = new ServerBuilder(PORT)
                .addServlet(new ImageCardsServlet(), ImageCardsServlet.IMAGE_CARD_PATH)
                .addServlet(new AdminAccessServlet(context), AdminAccessServlet.ADMIN_ACCESS_PATH)
                .addServlet(new NewClientServlet(), NewClientServlet.NEW_CLIENT_PATH)
                .build();

        server.start();
        log.info("The server was started");
        server.join();
    }

}
