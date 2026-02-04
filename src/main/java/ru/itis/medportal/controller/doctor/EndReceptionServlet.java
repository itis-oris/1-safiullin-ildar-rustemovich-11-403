package ru.itis.medportal.controller.doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ru.itis.medportal.exception.ValidationException;
import ru.itis.medportal.service.AppointmentService;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;

@WebServlet("/doctormenu/ended")
@MultipartConfig(
        location = "C:/uploads/medportal",
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20,
        fileSizeThreshold = 1024 * 1024
)
public class EndReceptionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resource = req.getAttribute("contextPath") + "/doctormenu";
        boolean flag = req.getParameter("flag") == null;
        try {
            save(req.getParts(),req.getParameter("flag"));
        } catch (ValidationException e) {
            String error = URLEncoder.encode(e.getMessage(), "UTF-8");
            if (flag) resource = req.getAttribute("contextPath") + "/doctormenu/workinghours?error=" + error;
            else resource = req.getAttribute("contextPath") + "/doctormenu/story?error=" + error;
        }
        resp.sendRedirect(resource);
    }

    private void save(Collection<Part> parts, String reqFlag) throws ServletException, IOException {
        boolean flag = true;
        if (reqFlag != null) flag = false;
        AppointmentService appointmentService = (AppointmentService)getServletContext().getAttribute("appointmentService");
        appointmentService.updateFromDoctor(parts,flag);
    }
}
