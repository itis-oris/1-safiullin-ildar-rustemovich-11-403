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

@WebServlet("/doctormenu/update")
public class DoctorUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        DoctorDTO doctor = (DoctorDTO)session.getAttribute("doctor");
        req.setAttribute("doctor",doctor);
        req.setAttribute("error",req.getParameter("error"));
        SpecializationService specializationService = (SpecializationService) req.getServletContext().getAttribute("specializationService");
        req.setAttribute("specializations", specializationService.findAll());
        req.getRequestDispatcher("/doctorupdate.ftlh").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(update(req));
    }

    private String update(HttpServletRequest req) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        DoctorDTO doctorDTO = (DoctorDTO)session.getAttribute("doctor");
        PatientService patientService = (PatientService) getServletContext().getAttribute("patientService");
        DoctorService doctorService = (DoctorService) getServletContext().getAttribute("doctorService");
        try {
            if (!doctorDTO.getEmail().equals(req.getParameter("email"))) {
                patientService.checkEmail(req.getParameter("email"));
                doctorService.checkEmail(req.getParameter("email"));
            }
            Doctor doctor = new Doctor();
            UserRequestParserUtil.setUserFields(req,doctor);
            UserRequestParserUtil.setDoctorFields(doctor,req);
            doctorDTO = doctorService.update(doctor,doctorDTO);
            session.setAttribute("doctor", doctorDTO);
        } catch (ValidationException e) {
            String error = URLEncoder.encode(e.getMessage(), "UTF-8");
            return req.getAttribute("contextPath") + "/doctormenu/update?error=" + error;
        }
        return req.getAttribute("contextPath") + "/doctormenu";
    }
}
