package servlets;

import config.Context;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminAccessServlet extends HttpServlet {
    public static final String ADMIN_ACCESS_PATH = "/admin";
    private Context context;

    public AdminAccessServlet(Context context) {
        this.context = context;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String session = req.getSession().getId();
        if (!context.checkSession(session)){
            resp.sendRedirect("admin.html");
        }else {
            Map<String, Object> map = new HashMap<>();
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(context.getPageGenerator().getPage("admin_panel.html", map));
        }

    }
}
