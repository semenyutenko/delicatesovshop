package servlets;

import config.Context;
import lombok.extern.java.Log;
import service.Executor;
import javax.servlet.http.*;
import java.io.IOException;


@Log
public class AddItemServlet extends HttpServlet {
    public static final String NEW_CLIENT_PATH = "/add-item";
    Context context;

    public AddItemServlet(Context context){
        this.context = context;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
        String area = req.getParameter("area");

        switch (area){
            case "clients": addClient(req, resp);
            break;
        }

    }

    private void addClient(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        Executor executor = context.getExecutor();

        if(context.checkSession(req)){
            String update = "insert into clients (client_name, client_phone, client_comment) values('" +
                    name + "', '" + phone + "', '" + req.getParameter("comment") + "');";
            int updated = executor.execUpdate(update);
            if(updated != 1){
                resp.getWriter().print("КЛИЕНТ НЕ БЫЛ ДОБАВЛЕН. ПОПРОБУЙТЕ ПОЗЖЕ");
            }else {
                resp.getWriter().print("КЛИЕНТ ДОБАВЛЕН");
            }
            return;
        }

        resp.sendRedirect("/admin");
    }
}
