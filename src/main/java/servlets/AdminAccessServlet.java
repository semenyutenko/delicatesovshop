package servlets;


import config.Context;
import lombok.extern.java.Log;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Log
public class AdminAccessServlet extends HttpServlet {
    public static final String ADMIN_ACCESS_PATH = "/admin";

    private final Context context;

    public AdminAccessServlet(Context context){
        this.context = context;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> pageVariables = getPageVariables(req);
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName() == "accessToken" && cookie.getValue() == context.getAccessToken()){
                    resp.getWriter().println(context.getPageGenerator().getPage("", pageVariables));
                    return;
                }
            }
        }
        HttpSession session = req.getSession();
        if(session == null){
            log.warning("There isn't any session");
        }else {log.info("The session's number is " + session.getId());
            session.setAttribute("pass", "hellboy");
            log.info("pass: " + session.getAttribute("pass"));
        }

        resp.getWriter().println(context.getPageGenerator().getPage("admin_access.html", pageVariables));

    }

    private Map<String, Object> getPageVariables(HttpServletRequest req){
        return new HashMap<String, Object>();
    }
}
