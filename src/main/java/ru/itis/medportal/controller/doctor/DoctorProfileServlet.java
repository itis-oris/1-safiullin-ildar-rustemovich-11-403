package ru.itis.medportal.controller.doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.medportal.dto.DoctorDTO;
import ru.itis.medportal.service.DoctorService;

import java.io.IOException;

@WebServlet("/doctormenu")
public class DoctorProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        DoctorDTO doctor = (DoctorDTO)session.getAttribute("doctor");
        req.setAttribute("doctor", doctor);
        req.getRequestDispatcher("/doctormenu.ftlh").forward(req, resp);
    }
}