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
public class UpdateAdminServlet extends HttpServlet {
    public static final String PATH = "/update-admin";
    Context context;

    public UpdateAdminServlet(Context context){
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
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
        String query = "select * from " + area + ";";
        Map<String, Object> map = executor.execQuery(query, rs -> {
            Map<String, Object> result = new HashMap<>();
            result.put("clients", new ArrayList<>());
            result.put("products", new ArrayList<>());
            result.put("orders", new ArrayList<>());
            result.put("categories", new ArrayList<>());
            List<String> list = new ArrayList<>();
            while (rs.next()){
                int count = rs.getMetaData().getColumnCount();
                StringBuilder string = new StringBuilder("<tr data-toggle=\"modal\" data-target=\"#edit_"
                        + area + "\" data-id=\"" +  rs.getLong(1) + "\" style=\"cursor: pointer\">");
                for (int i = 2; i <= count; i++){
                    if (i == count){
                        String comment = rs.getObject(i).toString().length() < 40 ? (rs.getObject(i).toString()) :
                                (rs.getObject(i).toString().substring(0, 40) + "...");
                        string.append("<td scope=\"col\" class=\"item d-none d-sm-table-cell w-50 overflow-auto\">"
                                + comment + "</td>");
                    }else {
                        string.append("<td scope=\"col\" class=\"item d-table-cell\">"
                                + rs.getObject(i).toString() + "</td>");
                    }
                }
                string.append("</tr>");
                list.add(string.toString());
            }
            result.put(area, list);
            return result;
        });
        resp.getWriter().print(context.getPageGenerator().getPage("update-admin.html", map));
    }

}
