package ru.geekbrains.lesson2.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class HeaderFooterFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(HeaderFooterFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("Adding header and footer to {}", servletRequest.getServletContext().getContextPath());

        servletResponse.getWriter().println("<h1>Header from filter</h1><hr>");
        filterChain.doFilter(servletRequest, servletResponse);
        servletResponse.getWriter().println("<hr><p>Footer from filter</p>");
    }

    @Override
    public void destroy() {
        logger.info("Filter destroy");
    }
}
