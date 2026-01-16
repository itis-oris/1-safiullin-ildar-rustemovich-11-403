package ru.itis.medportal.controller.doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.medportal.dto.DoctorDTO;
import ru.itis.medportal.dto.PatientDTO;
import ru.itis.medportal.model.Appointment;
import ru.itis.medportal.service.AppointmentService;

import java.io.IOException;
import java.util.*;

@WebServlet("/doctormenu/story")
public class DoctorStoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        DoctorDTO doctorDTO = (DoctorDTO) session.getAttribute("doctor");
        AppointmentService appointmentService = (AppointmentService) getServletContext().getAttribute("appointmentService");
        List<Map.Entry<Appointment, PatientDTO>> appointmentsList = appointmentService.getByUserId(doctorDTO.getId(),PatientDTO.class,true);
        request.setAttribute("appointments", appointmentsList);
        request.setAttribute("error",request.getParameter("error"));
        request.getRequestDispatcher("/doctorstory.ftlh").forward(request, response);

    }
}
