package ru.itis.medportal.controller.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.medportal.dto.DoctorDTO;
import ru.itis.medportal.dto.PatientDTO;
import ru.itis.medportal.exception.ValidationException;
import ru.itis.medportal.service.DoctorService;
import ru.itis.medportal.service.PatientService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errormessage", request.getParameter("errormessage"));
        request.getRequestDispatcher("/login.ftlh").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(login(req));
    }

    private String login(HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession(false);
        String resource = request.getAttribute("contextPath") + "/";
        String error;
        try {
            if(session == null || (session.getAttribute("doctor") == null && session.getAttribute("patient") == null && session.getAttribute("admin") == null)) {
                DoctorService doctorService = (DoctorService) getServletContext().getAttribute("doctorService");
                PatientService patientService = (PatientService) getServletContext().getAttribute("patientService");
                DoctorDTO doctor = doctorService.checkAccount(request.getParameter("email"), request.getParameter("password"));
                PatientDTO patient = patientService.checkAccount(request.getParameter("email"), request.getParameter("password"));
                if(doctor != null) {
                    session =  request.getSession(true);
                    session.setAttribute("doctor", doctor);
                    resource = request.getAttribute("contextPath") + "/main";
                } else if (patient != null) {
                    session =  request.getSession(true);
                    session.setAttribute("patient", patient);
                    resource = request.getAttribute("contextPath") + "/main";
                } else if ("admin@mail.ru".equals(request.getParameter("email")) && "admin".equals(request.getParameter("password"))) {
                    session =  request.getSession(true);
                    session.setAttribute("admin", "admin");
                    resource = request.getAttribute("contextPath") + "/admin/show";
                } else {
                    error = URLEncoder.encode("Неверное имя пользователя или пароль", "utf-8");
                    resource = request.getAttribute("contextPath") + "/login?errormessage=" + error;
                }
            }
        } catch (ValidationException e) {
            error = URLEncoder.encode(e.getMessage(), "UTF-8");
            resource = request.getAttribute("contextPath") + "/login?errormessage="+error;
        }
        return resource;
    }
}
