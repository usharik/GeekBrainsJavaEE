package ru.geekbrains.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/secure/*")
public class SecurityFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (httpRequest.getCookies() != null) {
            for (Cookie cookie : httpRequest.getCookies()) {
                logger.info("Cookie {} : {}", cookie.getName(), cookie.getValue());
            }
        } else {
            logger.info("No cookies");
        }

        if (session == null) {
            logger.info("No logged in redirecting to login page");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
        } else {
            String user = (String) session.getAttribute("user");
            logger.info("User {} is logged in", user);

            httpResponse.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
            httpResponse.getWriter().println("<p>User " + user + " is logged in. <a href=\"logout_serv\">Logout</a></p>");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
