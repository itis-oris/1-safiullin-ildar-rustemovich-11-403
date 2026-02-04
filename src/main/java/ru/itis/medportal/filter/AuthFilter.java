package ru.itis.medportal.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final Set<String> EXCLUDED_PATHS = Set.of(
            "/login", "/register", "/logout", "/doctors", "/main", "/static","/download"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(false);

        if (checkExcluded(((HttpServletRequest)servletRequest).getServletPath())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (session != null && (session.getAttribute("doctor") != null || session.getAttribute("patient") != null || session.getAttribute("admin") != null)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    private boolean checkExcluded(String resource) {
        if ("".equals(resource)) {
            return true;
        }
        return EXCLUDED_PATHS.stream().anyMatch(resource::startsWith);
    }
}
