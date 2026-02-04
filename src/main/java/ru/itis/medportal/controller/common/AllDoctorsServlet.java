package ru.itis.medportal.controller.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.medportal.service.DoctorService;

import java.io.IOException;

@WebServlet("/doctors")
public class AllDoctorsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DoctorService doctorService = (DoctorService)getServletContext().getAttribute("doctorService");
        req.setAttribute("doctors",doctorService.getAll());
        req.getRequestDispatcher("/doctors.ftlh").forward(req,resp);
    }
}
