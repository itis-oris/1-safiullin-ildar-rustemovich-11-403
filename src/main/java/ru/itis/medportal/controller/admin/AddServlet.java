package ru.itis.medportal.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.medportal.service.SpecializationService;

import java.io.IOException;

@WebServlet("/admin/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SpecializationService specializationService = (SpecializationService) getServletContext().getAttribute("specializationService");
        specializationService.save(request);
        response.sendRedirect(request.getAttribute("contextPath") + "/admin/show");
    }
}
