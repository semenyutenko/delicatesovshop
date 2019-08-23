package servlets;

import lombok.extern.java.Log;
import templater.ImageInsertor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Log
public class ImageCardsServlet extends HttpServlet {
    public static final String IMAGE_CARD_PATH = "/imagecards";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImageInsertor.insertImage(resp, req.getParameter("image") + ".jpg");
        log.info("The image is downloaded");
    }
}
