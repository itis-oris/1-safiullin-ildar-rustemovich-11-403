package ru.itis.medportal.controller.patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.medportal.dto.PatientDTO;
import ru.itis.medportal.exception.ValidationException;
import ru.itis.medportal.model.Patient;
import ru.itis.medportal.service.DoctorService;
import ru.itis.medportal.service.PatientService;
import ru.itis.medportal.util.UserRequestParserUtil;


import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/register/patient")
public class PatientRegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error",req.getParameter("error"));
        req.getRequestDispatcher("/patientregister.ftlh").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.sendRedirect(register(req));
    }

    private String register(HttpServletRequest req) throws IOException{
        String email = req.getParameter("email");
        String resource;
        String error;
        PatientService patientService = (PatientService) getServletContext().getAttribute("patientService");
        DoctorService doctorService = (DoctorService) getServletContext().getAttribute("doctorService");
        try{
            patientService.checkEmail(email);
            doctorService.checkEmail(email);
            Patient patient = new Patient();
            UserRequestParserUtil.setUserFields(req,patient);
            UserRequestParserUtil.setPatientFields(patient,req);
            PatientDTO patientDTO = patientService.save(patient);
            HttpSession session = req.getSession(true);
            session.setAttribute("patient", patientDTO);
            resource = req.getAttribute("contextPath") + "/patientmenu";
        } catch (ValidationException e) {
            error = URLEncoder.encode(e.getMessage(),"UTF-8");
            resource = req.getAttribute("contextPath") + "/register/patient?error="+error;
        }
        return resource;
    }
}
