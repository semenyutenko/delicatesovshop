package servlets;

import config.Context;
import lombok.extern.java.Log;
import service.Executor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Log
public class EditItemServlet extends HttpServlet {
    public static final String PATH = "/edit-item";
    Context context;
    String id;

    public EditItemServlet(Context context){
        this.context = context;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
        String area = req.getParameter("area");
        id = req.getParameter("id");

        if(!context.checkSession(req)){
            resp.sendRedirect("/admin");
            return;
        }

        switch (area){
            case "clients": editClient(req, resp);
            break;
        }

    }

    private void editClient(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String name = context.validateString(req.getParameter("name"));
        String phone = context.validateString(req.getParameter("phone"));
        String comment = context.validateString(req.getParameter("comment"));
        Executor executor = context.getExecutor();

        String update = "update clients set client_name='" + name + "', client_phone='" + phone + "', client_comment='" + comment
                + "' where client_id='" + id + "';";
        int updated = executor.execUpdate(update);
        resp.getWriter().print(updated);
        return;
    }
}
