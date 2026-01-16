package ru.itis.medportal.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.medportal.service.SpecializationService;

import java.io.IOException;

@WebServlet("/admin/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SpecializationService specializationService = (SpecializationService)getServletContext().getAttribute("specializationService");
        specializationService.deleteSpecialization(Long.parseLong(request.getParameter("specId")));
        response.sendRedirect(request.getAttribute("contextPath") + "/admin/show");
    }
}
