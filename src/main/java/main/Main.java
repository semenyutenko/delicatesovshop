package main;

import config.Context;
import lombok.extern.java.Log;
import org.eclipse.jetty.server.Server;
import service.Executor;
import service.ServerBuilder;
import servlets.AdminAccessServlet;
import servlets.ImageCardsServlet;
import servlets.AddClientServlet;
import servlets.UpdateAdminServlet;

import java.sql.SQLException;

@Log
public class Main {

    private static int PORT;

    public static void main(String[] args) throws Exception {

        Context context = new Context(args[0]);
        PORT = context.getServerPort();
        prepareDB(context);

        Server server = new ServerBuilder(PORT)
                .addServlet(new ImageCardsServlet(), ImageCardsServlet.IMAGE_CARD_PATH)
                .addServlet(new AdminAccessServlet(context), AdminAccessServlet.ADMIN_ACCESS_PATH)
                .addServlet(new AddClientServlet(context), AddClientServlet.NEW_CLIENT_PATH)
                .addServlet(new UpdateAdminServlet(context), UpdateAdminServlet.UPDATE_ADMIN_PATh)
                .build();

        server.start();
        log.info("The server was started");
        server.join();
    }

    private static void prepareDB(Context context) throws SQLException {
        Executor executor = context.getExecutor();
        executor.execUpdate("create table if not exists public.clients " +
                "(client_id serial NOT NULL, client_name character varying(250) NOT NULL, " +
                "client_phone character varying(20) NOT NULL, client_comment character varying, " +
                "PRIMARY KEY (client_id))");
    }

}
