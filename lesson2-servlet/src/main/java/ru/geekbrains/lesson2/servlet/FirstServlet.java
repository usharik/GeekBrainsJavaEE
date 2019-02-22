package ru.geekbrains.lesson2.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Информация для привязки данного класса-сервелета к URL
 * находится в файле web.xml
 */
public class FirstServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(FirstServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New GET request");

        calc(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New POST request");

        calc(req, resp);
    }

    private void calc(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Integer a = Integer.valueOf(req.getParameter("a"));
            Integer b = Integer.valueOf(req.getParameter("b"));

            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().printf("<p>Result: %d + %d = %d</p>", a, b, a+b);
        } catch (Exception ex) {
            logger.error("Exception", ex);
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().println("<p>Incorrect parameters</p>");
            resp.setStatus(400);
        }
    }
}
