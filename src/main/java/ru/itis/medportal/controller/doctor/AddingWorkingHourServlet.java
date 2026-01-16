package ru.itis.medportal.controller.doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.medportal.dto.DoctorDTO;
import ru.itis.medportal.exception.ValidationException;
import ru.itis.medportal.service.AppointmentService;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;

@WebServlet("/doctormenu/addworkinghour")
public class AddingWorkingHourServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error",req.getParameter("error"));
        req.getRequestDispatcher("/addworkinghour.ftlh").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AppointmentService appointmentService = (AppointmentService) getServletContext().getAttribute("appointmentService");
        HttpSession session = request.getSession(false);
        DoctorDTO doctorDTO = (DoctorDTO) session.getAttribute("doctor");
        String resourse = request.getAttribute("contextPath") + "/doctormenu/workinghours";
        try {
            appointmentService.save(LocalDateTime.parse(request.getParameter("scheduledTime")),doctorDTO.getId());
        } catch (ValidationException e) {
            String error = URLEncoder.encode(e.getMessage(),"UTF-8");
            resourse = request.getAttribute("contextPath") + "/doctormenu/addworkinghour?error="+error;
        }
        response.sendRedirect(resourse);
    }
}
