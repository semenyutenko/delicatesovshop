import lombok.extern.java.Log;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

@Log
public class Main {

    private static final String PUBLIC_DIR = "public_html";
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_DIR);

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(PORT);
        server.setHandler(handlerList);
        server.start();
        log.info("The server has started");
        server.join();
    }
}
