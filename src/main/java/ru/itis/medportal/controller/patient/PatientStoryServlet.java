package ru.itis.medportal.controller.patient;

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

@WebServlet("/patientmenu/story")
public class PatientStoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PatientDTO patientDTO = (PatientDTO) session.getAttribute("patient");
        AppointmentService appointmentService = (AppointmentService) getServletContext().getAttribute("appointmentService");
        List<Map.Entry<Appointment, DoctorDTO>> appointmentsList = appointmentService.getByUserId(patientDTO.getId(),DoctorDTO.class,false);
        request.setAttribute("appointments", appointmentsList);
        request.getRequestDispatcher("/patientstory.ftlh").forward(request, response);
    }
}