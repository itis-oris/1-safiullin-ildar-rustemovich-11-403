package ru.itis.medportal.controller.patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.medportal.service.AppointmentService;
import ru.itis.medportal.service.DoctorService;

import java.io.IOException;

@WebServlet("/patientmenu/reception")
public class ReceptionSerlvet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DoctorService doctorService = (DoctorService)getServletContext().getAttribute("doctorService");
        req.setAttribute("doctors", doctorService.getAll());
        req.getRequestDispatcher("/reception.ftlh").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppointmentService appointmentService = (AppointmentService)getServletContext().getAttribute("appointmentService");
        Long id = Long.parseLong(req.getParameter("doctorId"));
        req.setAttribute("appointments", appointmentService.getByDoctorForPatient(id));
        req.getRequestDispatcher("/ajax.ftlh").forward(req, resp);
    }
}
