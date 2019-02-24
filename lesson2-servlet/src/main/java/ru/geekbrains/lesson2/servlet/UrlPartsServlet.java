package ru.geekbrains.lesson2.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UrlPartsServlet", urlPatterns = "/url_part_servlet/*")
public class UrlPartsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<p> req.getContextPath() " + req.getContextPath() + "</p>");
        resp.getWriter().println("<p> req.getServletPath() " + req.getServletPath() + "</p>");
        resp.getWriter().println("<p> req.getPathInfo() " + req.getPathInfo() + "</p>");
        resp.getWriter().println("<p> req.getQueryString() " + req.getQueryString() + "</p>");
    }
}
