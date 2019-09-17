package servlets;

import lombok.extern.java.Log;
import org.eclipse.jetty.server.Request;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


@Log
public class NewClientServlet extends HttpServlet {
    public static final String NEW_CLIENT_PATH = "/new-client";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("Session is: " + session.getId());
        resp.getWriter().println("Name is: " + req.getParameter("name"));
        resp.getWriter().println("Phone is: " + req.getParameter("phone"));
        resp.getWriter().println("Comment is: " + req.getParameter("comment"));

    }
}
