package ru.itis.medportal.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)servletRequest).getSession(false);
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        if (session == null) {
            ((HttpServletResponse)servletResponse).sendRedirect(request.getAttribute("contextPath") + "/login");
            return;
        }

        if(session.getAttribute("admin") == null) {
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
