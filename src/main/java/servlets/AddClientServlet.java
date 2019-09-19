package servlets;

import config.Context;
import lombok.extern.java.Log;
import org.eclipse.jetty.server.Request;
import service.Executor;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;


@Log
public class AddClientServlet extends HttpServlet {
    public static final String NEW_CLIENT_PATH = "/add-client";
    Context context;

    public AddClientServlet(Context context){
        this.context = context;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Executor executor = context.getExecutor();
        String update = "insert into clients (client_name, client_phone, client_comment) values('" +
                req.getParameter("name") + "', '" + req.getParameter("phone") + "', '" +
                req.getParameter("comment") + "');";
        try {
            executor.execUpdate(update);
            log.info("Строка: " + update + " выполнена");
        } catch (SQLException e) {
            log.warning("Не прошло добавление клиента");
            e.printStackTrace();
        }

    }
}
