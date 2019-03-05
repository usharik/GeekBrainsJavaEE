package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*.jsp")
public class ContentFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(ContentFilter.class);

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("Content filter {}", ((HttpServletRequest) request).getPathInfo());
        response.setContentType("text/html");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
