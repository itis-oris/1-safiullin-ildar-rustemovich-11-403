package ru.itis.medportal.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/login", "/register"})
public class AuthRedirectFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        if (session != null && (session.getAttribute("doctor") != null || session.getAttribute("patient") != null)) {
            if (session.getAttribute("doctor") != null) {
                resp.sendRedirect(request.getAttribute("contextPath") + "/doctormenu");
            } else {
                resp.sendRedirect(request.getAttribute("contextPath") + "/patientmenu");
            }
            return;
        }

        chain.doFilter(request, response);
    }
}