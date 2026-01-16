package ru.itis.medportal.controller.patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.medportal.dto.PatientDTO;
import ru.itis.medportal.exception.ValidationException;
import ru.itis.medportal.service.AppointmentService;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/patientmenu/book")
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("appointmentId", req.getParameter("appointmentId"));
        req.setAttribute("error",req.getParameter("error"));
        req.getRequestDispatcher("/book.ftlh").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PatientDTO patientDTO = (PatientDTO)  req.getSession().getAttribute("patient");
        AppointmentService appointmentService =(AppointmentService) getServletContext().getAttribute("appointmentService");
        String resourse = req.getAttribute("contextPath") + "/patientmenu";
        try {
            appointmentService.updateFromPatient(req.getParameter("appointmentId"),req.getParameter("complaints"),patientDTO);
        } catch (ValidationException e) {
            String error = URLEncoder.encode(e.getMessage(),"UTF-8");
            resourse = req.getAttribute("contextPath") + "/patientmenu/book?error="+error+"&appointmentId="+req.getParameter("appointmentId");
        }
        resp.sendRedirect(resourse);
    }
}
