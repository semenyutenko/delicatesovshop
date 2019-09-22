package servlets;

import config.Context;
import lombok.extern.java.Log;
import service.Executor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Log
public class UpdateAdminServlet extends HttpServlet {
    public static final String UPDATE_ADMIN_PATh = "/update_admin";
    Context context;

    public UpdateAdminServlet(Context context){
        this.context = context;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        HttpSession session = req.getSession();
        Executor executor = context.getExecutor();
        String area = req.getParameter("area");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");

        if(!context.checkSession(session.getId())){
            resp.getWriter().println("У ВАС НЕТ ДОСТУПА К ЭТОЙ ОПЕРАЦИИ");
            return;
        }
        String query = "select * from " + area + ";";
        Map<String, Object> map = executor.execQuery(query, rs -> {
            Map<String, Object> result = new HashMap<>();
            List<String> list = new ArrayList<>();
            while (rs.next()){
                int count = rs.getMetaData().getColumnCount();
                StringBuilder string = new StringBuilder("<tr>");
                for (int i = 2; i <= count; i++){
                    string.append("<td>" + rs.getObject(i).toString() + "</td>");
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
