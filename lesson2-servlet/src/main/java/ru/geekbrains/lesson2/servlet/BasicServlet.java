package ru.geekbrains.lesson2.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "BasicServlet", urlPatterns = "/basic_servlet")
public class BasicServlet implements Servlet {

    private static Logger logger = LoggerFactory.getLogger(BasicServlet.class);

    private transient ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        logger.info("Servlet name {}", config.getServletName());
        logger.info("Servlet context path {}", config.getServletContext().getContextPath());
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        logger.info("New request");
        ServletContext context = config.getServletContext();
        res.getWriter().println("<h1>Servlet content</h1>");
        context.getRequestDispatcher("/WEB-INF/forward.html").include(req, res);
        context.getRequestDispatcher("/").include(req, res);
    }

    @Override
    public String getServletInfo() {
        return "BasicServlet";
    }

    @Override
    public void destroy() {
        logger.info("Servlet {} destroyed", getServletInfo());
    }
}
