package ru.itis.medportal.controller.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.ftlh").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role =  request.getParameter("role");
        if (role.equals("doctor")) {
            response.sendRedirect(request.getAttribute("contextPath") + "/register/doctor");
        } else {
            response.sendRedirect(request.getAttribute("contextPath") + "/register/patient");
        }
    }
}
