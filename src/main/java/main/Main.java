package main;

import config.Context;
import lombok.extern.java.Log;
import org.eclipse.jetty.server.Server;
import service.ServerBuilder;
import servlets.AdminAccessServlet;
import servlets.ImageCardsServlet;

@Log
public class Main {

    private static int PORT;

    public static void main(String[] args) throws Exception {

        Context context = new Context(args[0]);
        PORT = context.getServerPort();

        Server server = new ServerBuilder(PORT)
                .addServlet(new ImageCardsServlet(), ImageCardsServlet.IMAGE_CARD_PATH)
                .addServlet(new AdminAccessServlet(), AdminAccessServlet.ADMIN_ACCESS_PATH)
                .build();

        server.start();
        log.info("The server was started");
        server.join();
    }

}
