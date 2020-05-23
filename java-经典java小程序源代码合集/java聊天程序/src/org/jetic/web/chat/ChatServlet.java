package org.jetic.web.chat;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class ChatServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html";
    /**Initialize global variables*/
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            new Server();
        }
        catch (IOException ex) {
            System.err.println("IO ´íÎó£º");
            ex.printStackTrace(System.err);
            destroy();
        }
    }
    /**Process the HTTP Get request*/
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>ChatServlet</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a GET. This is the reply.</p>");
        out.println("</body></html>");
    }
    /**Clean up resources*/
    public void destroy() {
    }
}