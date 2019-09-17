package servlets;

import config.Context;
import lombok.extern.java.Log;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Log
public class AdminAccessServlet extends HttpServlet {
    public static final String ADMIN_ACCESS_PATH = "/admin";
    private Context context;

    public AdminAccessServlet(Context context) {
        this.context = context;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(86400);
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);

        if (context.checkSession(cookie.getValue())){
            Map<String, Object> map = new HashMap<>();
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(context.getPageGenerator().getPage("admin-panel.html", map));
        }else {
            Map<String, Object> map = new HashMap<>();
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(context.getPageGenerator().getPage("admin-access.html", map));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pass = req.getParameter("pass");
        HttpSession session = req.getSession();
        String sessionId = session.getId();
        resp.setStatus(HttpServletResponse.SC_SEE_OTHER);

        long lastAccessTime = session.getLastAccessedTime();
        long currentTime;
        do{
            currentTime = new Date().getTime();
        }while ((currentTime - lastAccessTime) / 1000 < 10);

        if (context.checkPass(pass)){
            context.setAdminSession(sessionId);
            resp.sendRedirect("/admin");
        }else {
            resp.sendRedirect("/admin?pass=incorrect");
        }



    }
}
