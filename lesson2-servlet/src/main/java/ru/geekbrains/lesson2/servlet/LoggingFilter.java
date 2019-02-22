package ru.geekbrains.lesson2.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "LoggingFilter", urlPatterns = "/*")
public class LoggingFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(HeaderFooterFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        logger.info("Logging filter. New request. Method {}. Host {}. Path {}",
                httpRequest.getMethod(), httpRequest.getRemoteHost(), httpRequest.getPathInfo());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("Filter destroy");
    }
}
