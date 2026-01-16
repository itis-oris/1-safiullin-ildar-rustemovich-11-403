package ru.itis.medportal.controller.patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.medportal.dto.PatientDTO;

import java.io.IOException;

@WebServlet("/patientmenu")
public class PatientProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PatientDTO patient = (PatientDTO) session.getAttribute("patient");
        req.setAttribute("patient", patient);
        req.getRequestDispatcher("/patientmenu.ftlh").forward(req, resp);
    }
}
