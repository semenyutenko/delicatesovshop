package servlets;

import config.Context;
import lombok.extern.java.Log;
import service.Executor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Log
public class SelectByIdServlet extends HttpServlet {
    public static final String SELECT_BY_ID_PATh = "/select-by-id";
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
        String query = "select * from " + area + " where " + area.substring(0, area.length() - 1) +
                "_id = " + id + ";";
        log.info(query);
        Map<String, Object> map;

        switch (area){
            case "clients": map = executor.execQuery(query, rs -> {
                Map<String, Object> result = new HashMap<>();
                rs.next();
                result.put(area, "plug");
                return result;
                });
                break;
            default: resp.sendRedirect("/admin");
                return;
        }
//        resp.getWriter().print(context.getPageGenerator().getPage("update-admin.html", map));
    }

}
