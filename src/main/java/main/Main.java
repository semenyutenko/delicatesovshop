package main;

import config.Context;
import lombok.extern.java.Log;
import org.eclipse.jetty.server.Server;
import service.Executor;
import service.ServerBuilder;
import servlets.*;

import java.sql.SQLException;

@Log
public class Main {

    private static int PORT;

    public static void main(String[] args) throws Exception {

        Context context = new Context(args[0]);
        PORT = context.getServerPort();
        prepareDB(context);

        Server server = new ServerBuilder(PORT)
                .addServlet(new ImageCardsServlet(), ImageCardsServlet.PATH)
                .addServlet(new AdminAccessServlet(context), AdminAccessServlet.PATH)
                .addServlet(new AddItemServlet(context), AddItemServlet.PATH)
                .addServlet(new UpdateAdminServlet(context), UpdateAdminServlet.PATH)
                .addServlet(new SelectByIdServlet(context), SelectByIdServlet.PATH)
                .addServlet(new DellItemServlet(context), DellItemServlet.PATH)
                .addServlet(new EditItemServlet(context), EditItemServlet.PATH)
                .build();

        server.start();
        log.info("The server was started");
        server.join();
    }

    private static void prepareDB(Context context) throws SQLException {
        Executor executor = context.getExecutor();
        executor.execUpdate("create table if not exists public.clients " +
                "(client_id serial NOT NULL, client_name character varying(250) NOT NULL, " +
                "client_phone character varying(20) NOT NULL, client_comment text, " +
                "PRIMARY KEY (client_id))");
        executor.execUpdate("create table if not exists public.products " +
                "(product_id serial NOT NULL, product_title character varying(250) NOT NULL, " +
                "product_category character varying(50) NOT NULL, product_price numeric NOT NULL," +
                " product_amount numeric NOT NULL, product_description text, " +
                "PRIMARY KEY (product_id))");

    }

}
