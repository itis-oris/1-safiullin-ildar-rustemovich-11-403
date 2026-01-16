package ru.itis.medportal.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/doctormenu/*", "/patientmenu/*"})
public class DoctorPatientFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        String path = req.getServletPath();
        if (path.startsWith("/doctormenu")) {
            if (session.getAttribute("doctor") == null) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        } else if (path.startsWith("/patientmenu")) {
            if (session.getAttribute("patient") == null) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}