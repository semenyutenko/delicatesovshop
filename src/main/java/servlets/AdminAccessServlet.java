package servlets;

import config.Context;
import lombok.extern.java.Log;
import org.eclipse.jetty.server.session.DefaultSessionIdManager;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> map = new HashMap<>();
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);

        if (context.checkSession(req)){
            resp.getWriter().println(context.getPageGenerator().getPage("admin-panel.html", map));
            return;
        }
        resp.getWriter().println(context.getPageGenerator().getPage("admin-access.html", map));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pass = req.getParameter("pass");
        HttpSession session = req.getSession();
        String sessionId = session.getId();
        resp.setStatus(HttpServletResponse.SC_SEE_OTHER);

        long lastAccessTime = session.getLastAccessedTime();
        long currentTime;
        do{
            currentTime = new Date().getTime();
        }while ((currentTime - lastAccessTime) / 1000 < 5);

        if (context.checkPass(pass)){
            Cookie cookie = new Cookie("SESSIONID", sessionId);
            cookie.setMaxAge(8640000);
            resp.addCookie(cookie);
            context.setAdminSession(sessionId);
            resp.sendRedirect("/admin");
        }else {
            resp.sendRedirect("/admin?pass=incorrect");
        }



    }
}
