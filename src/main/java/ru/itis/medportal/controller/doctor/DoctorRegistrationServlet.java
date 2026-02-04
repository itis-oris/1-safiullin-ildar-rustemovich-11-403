package ru.itis.medportal.controller.doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.medportal.dto.DoctorDTO;
import ru.itis.medportal.exception.ValidationException;
import ru.itis.medportal.model.Doctor;
import ru.itis.medportal.service.DoctorService;
import ru.itis.medportal.service.PatientService;
import ru.itis.medportal.service.SpecializationService;
import ru.itis.medportal.util.UserRequestParserUtil;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/register/doctor")
public class DoctorRegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SpecializationService specializationService = (SpecializationService) req.getServletContext().getAttribute("specializationService");
        req.setAttribute("specializations", specializationService.findAll());
        req.setAttribute("error",req.getParameter("error"));
        req.getRequestDispatcher("/doctorregister.ftlh").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.sendRedirect(register(req));
    }

    private String register(HttpServletRequest req) throws IOException{
        String email = req.getParameter("email");
        String resource;
        String error;
        DoctorService doctorService = (DoctorService) req.getServletContext().getAttribute("doctorService");
        PatientService patientService = (PatientService) req.getServletContext().getAttribute("patientService");
        try {
            doctorService.checkEmail(email);
            patientService.checkEmail(email);
            Doctor doctor = new Doctor();
            UserRequestParserUtil.setUserFields(req,doctor);
            UserRequestParserUtil.setDoctorFields(doctor,req);
            DoctorDTO doctorDTO = doctorService.save(doctor);
            HttpSession session = req.getSession(true);
            session.setAttribute("doctor",doctorDTO);
            resource = req.getAttribute("contextPath") + "/doctormenu";
        } catch (ValidationException e) {
            error = URLEncoder.encode(e.getMessage(),"UTF-8");
            resource = req.getAttribute("contextPath") + "/register/doctor?error="+error;
        }
        return resource;
    }
}
