import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;

public class ServerBuilder {
    private static final String PUBLIC_DIR = "public_html";

    private final Server server;
    private final ResourceHandler resourceHandler;
    private final ServletContextHandler context;
    private final HandlerList handlerList;

    public ServerBuilder(final int port){
        this.server = new Server(port);
        this.resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_DIR);
        this.context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        this.handlerList = new HandlerList();
    }

    public ServerBuilder addServlet(final HttpServlet servlet, final String path){
        context.addServlet(new ServletHolder(servlet), path);
        return this;
    }

    public Server build(){
        handlerList.setHandlers(new Handler[]{resourceHandler, context});
        server.setHandler(handlerList);
        return server;
    }
}
