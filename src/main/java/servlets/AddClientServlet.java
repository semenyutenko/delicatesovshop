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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        HttpSession session = req.getSession();
        Executor executor = context.getExecutor();
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");

        if(!context.checkSession(session.getId())){
            resp.getWriter().println("У ВАС НЕТ ДОСТУПА К ЭТОЙ ОПЕРАЦИИ");
            return;
        }
        if(name.equals("")){
            resp.getWriter().println("ВЫ НЕ УКАЗАЛИ ИМЯ КЛИЕНТА");
            return;
        }
        if(phone.equals("")){
            resp.getWriter().println("ВЫ НЕ УКАЗАЛИ НОМЕР ТЕЛЕФОНА КЛИЕНТА");
            return;
        }

        String update = "insert into clients (client_name, client_phone, client_comment) values('" +
                name + "', '" + phone + "', '" + req.getParameter("comment") + "');";
        int updated = executor.execUpdate(update);
        if(updated != 1){
            resp.getWriter().println("КЛИЕНТ НЕ БЫЛ ДОБАВЛЕН. ПОПРОБУЙТЕ ПОЗЖЕ");
        }else {
            resp.getWriter().println("КЛИЕНТ ДОБАВЛЕН");
        }
    }
}
