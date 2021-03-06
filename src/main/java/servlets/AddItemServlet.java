package servlets;

import config.Context;
import lombok.extern.java.Log;
import service.Executor;
import javax.servlet.http.*;
import java.io.IOException;


@Log
public class AddItemServlet extends HttpServlet {
    public static final String PATH = "/add-item";
    Context context;

    public AddItemServlet(Context context){
        this.context = context;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
        String area = req.getParameter("area");
        if(!context.checkSession(req)){
            resp.sendRedirect("/admin");
            return;
        }

        switch (area){
            case "clients": addClient(req, resp);
                break;
            case "products": addProduct(req, resp);
                break;
        }

    }

    private void addClient(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String name = context.validateString(req.getParameter("name"));
        String phone = context.validateString(req.getParameter("phone"));
        String comment = context.validateString(req.getParameter("comment"));
        Executor executor = context.getExecutor();

        String update = "insert into clients (client_name, client_phone, client_comment) values('" +
                name + "', '" + phone + "', '" + comment + "');";
        int updated = executor.execUpdate(update);
        resp.getWriter().print(updated);
        return;
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String title = context.validateString(req.getParameter("title"));
        String category = context.validateString(req.getParameter("category"));
        String price = context.validateString(req.getParameter("price"));
        String amount = context.validateString(req.getParameter("amount"));
        String description = context.validateString(req.getParameter("description"));
        Executor executor = context.getExecutor();

        String update = "insert into products (product_title, product_category, product_price, product_amount, product_description)"
                + " values('" + title + "', '" + category + "', '" + price + "', '" + amount + "', '" + description + "');";
        int updated = executor.execUpdate(update);
        resp.getWriter().print(updated);
        return;
    }
}
