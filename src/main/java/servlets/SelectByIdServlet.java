package servlets;

import config.Context;
import lombok.extern.java.Log;
import service.Executor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Log
public class SelectByIdServlet extends HttpServlet {
    public static final String PATH = "/select-by-id";
    Context context;

    public SelectByIdServlet(Context context){
        this.context = context;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        if(!context.checkSession(req)){
            resp.sendRedirect("/admin");
            return;
        }

        Executor executor = context.getExecutor();
        String area = req.getParameter("area");
        String id = req.getParameter("id");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
        String queryString = "select * from " + area + " where " + area.substring(0, area.length() - 1) +
                "_id = " + id + ";";
        Map<String, Object> map;

        switch (area){
            case "clients": map = executor.execQuery(queryString, rs -> {
                Map<String, Object> result = new HashMap<>();
                rs.next();
                String name = rs.getObject("client_name").toString();
                String phone = rs.getObject("client_phone").toString();
                String comment = rs.getObject("client_comment").toString();
                result.put("name", name);
                result.put("phone", phone);
                result.put("comment", comment);
                return result;
                });
                break;
            default: resp.sendRedirect("/admin");
                return;
        }
        resp.getWriter().print(context.getPageGenerator().getPage("admin-edit-" + area + ".html", map));
    }

}
