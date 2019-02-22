package ru.geekbrains.lesson2.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Здесь информация для привязки указана при помощи аннотации
 */
@WebServlet(name = "SecondServlet", urlPatterns = "second_servlet/*")
public class SecondServlet extends HttpServlet {

    /**
     * Пример перенаправления (форварда) на скрытый внутренний ресурс
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/forward.html").forward(req, resp);
    }
}
