package templater;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;


public class ImageInsertor {

    public static final String IMAGE_FOLDER= "./public_html/images/";

    public static void insertImage(HttpServletResponse resp, String path) throws IOException {
        resp.setContentType("image/jpg");
        InputStream is = new FileInputStream(IMAGE_FOLDER + path);
        BufferedImage bi = ImageIO.read(is);
        OutputStream os = resp.getOutputStream();
        ImageIO.write(bi, "jpg", os);
    }
}
