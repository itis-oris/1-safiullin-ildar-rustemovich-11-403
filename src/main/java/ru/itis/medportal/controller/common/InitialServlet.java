package ru.itis.medportal.controller.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = {"/main",""})
public class InitialServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            req.setAttribute("doctor", session.getAttribute("doctor"));
            req.setAttribute("patient", session.getAttribute("patient"));
            req.setAttribute("admin", session.getAttribute("admin"));
        }
        req.getRequestDispatcher("/index.ftlh").forward(req,resp);
    }
}
