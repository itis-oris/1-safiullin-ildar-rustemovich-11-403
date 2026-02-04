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

@WebServlet("/patientmenu/update")
public class PatientUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PatientDTO patient = (PatientDTO) session.getAttribute("patient");
        req.setAttribute("patient",patient);
        req.setAttribute("error",req.getParameter("error"));
        req.getRequestDispatcher("/patientupdate.ftlh").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(update(req));
    }

    private String update(HttpServletRequest req) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        PatientDTO patientDTO = (PatientDTO) session.getAttribute("patient");
        PatientService patientService = (PatientService) getServletContext().getAttribute("patientService");
        DoctorService doctorService = (DoctorService) getServletContext().getAttribute("doctorService");
        try {
            if (!patientDTO.getEmail().equals(req.getParameter("email"))) {
                patientService.checkEmail(req.getParameter("email"));
                doctorService.checkEmail(req.getParameter("email"));
            }
            Patient patient = new Patient();
            UserRequestParserUtil.setUserFields(req,patient);
            UserRequestParserUtil.setPatientFields(patient,req);
            patientDTO = patientService.update(patient,patientDTO);
            session.setAttribute("patient", patientDTO);
        } catch (ValidationException e) {
            String error = URLEncoder.encode(e.getMessage(),"UTF-8");
            return req.getAttribute("contextPath") + "/patientmenu/update?error="+error;
        }
        return req.getAttribute("contextPath") + "/patientmenu";
    }
}
