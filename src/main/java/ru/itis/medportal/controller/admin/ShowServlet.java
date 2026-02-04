package ru.itis.medportal.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.medportal.service.SpecializationService;

import java.io.IOException;

@WebServlet("/admin/show")
public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SpecializationService specializationService = (SpecializationService)getServletContext().getAttribute("specializationService");
        request.setAttribute("specializations", specializationService.findAll());
        request.getRequestDispatcher("/show.ftlh").forward(request, response);
    }
}
