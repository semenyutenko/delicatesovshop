package servlets;

import config.Context;
import lombok.extern.java.Log;
import service.Executor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
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
        Map<String, Object> map = executor.execQuery(query, resultSet -> {
            Map<String, Object> result = new HashMap<>();
            while (resultSet.next()){
                log.info("Test: " + resultSet.get );
            }
            return result;
        });
    }
}
