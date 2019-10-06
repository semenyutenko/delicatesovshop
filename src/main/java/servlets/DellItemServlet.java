package servlets;

import config.Context;
import lombok.extern.java.Log;
import service.Executor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Log
public class DellItemServlet extends HttpServlet {
    public static final String PATH = "/dell-item";
    Context context;

    public DellItemServlet(Context context){
        this.context = context;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
        String area = req.getParameter("area");
        String id = req.getParameter("id");
        Executor executor = context.getExecutor();

        if(context.checkSession(req)){
            String update = "delete from " + area + " where " + area.substring(0, area.length() - 1) +
                    "_id = " + id + ";";
            int updated = executor.execUpdate(update);
            resp.getWriter().print(updated);
        }

        resp.sendRedirect("/admin");
    }
}
