package ru.itis.medportal.controller.doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.medportal.dto.DoctorDTO;
import ru.itis.medportal.service.AppointmentService;

import java.io.IOException;


@WebServlet("/doctormenu/workinghours")
public class WorkingHoursServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DoctorDTO doctorDTO = (DoctorDTO) req.getSession().getAttribute("doctor");
        AppointmentService appointmentService =(AppointmentService) getServletContext().getAttribute("appointmentService");
        req.setAttribute("appointmentMap", appointmentService.getByDoctorForDoctor(doctorDTO.getId()));
        req.setAttribute("error",req.getParameter("error"));
        req.getRequestDispatcher("/workinghours.ftlh").forward(req, resp);
    }
}
