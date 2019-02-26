package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет - контроллер
 */
@WebServlet(name = "ProductServletJstl", urlPatterns = "/product_jstl")
public class ProductServletJstl extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ProductServletJstl.class);

    private ProductRepository repository = new ProductRepository();

    @Override
    public void init() throws ServletException {
        logger.info("Servlet init {}", getClass().getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // помещаем список продуктов в аттрибут запроса
        // это более правильно чем использовать тут аттрибут сервлета через getServletContext()
        // или аттрибут сессии через getSession()
        req.setAttribute("products", repository.getAll());

        // переадресуемся на представление
        getServletContext().getRequestDispatcher("/WEB-INF/views/products-jstl.jsp")
                .forward(req, resp);
    }
}