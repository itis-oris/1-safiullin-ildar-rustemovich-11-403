package ru.itis.medportal.util;


import jakarta.servlet.http.HttpServletRequest;
import ru.itis.medportal.model.Doctor;
import ru.itis.medportal.model.Patient;
import ru.itis.medportal.model.Specialization;
import ru.itis.medportal.model.User;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserRequestParserUtil {

    public static <T extends User> void setUserFields(HttpServletRequest req, T user) {
        user.setEmail(req.getParameter("email"));
        user.setName(req.getParameter("name"));
        user.setSurname(req.getParameter("surname"));
        user.setPassword(req.getParameter("password"));
    }

    public static void setDoctorFields(Doctor doctor, HttpServletRequest req) {
        doctor.setAboutMe(req.getParameter("aboutMe"));
        doctor.setOnlineConsultations(req.getParameter("onlineConsultations").equals("да"));
        String[] specIds = req.getParameterValues("specializations");
        if (specIds != null) {
            List<Specialization> specializations = Arrays.stream(specIds)
                    .map(Long::parseLong)
                    .map(specId -> {
                        Specialization spec = new Specialization();
                        spec.setSpecId(specId);
                        return spec;
                    })
                    .collect(Collectors.toList());
            doctor.setSpecializations(specializations);
        }
    }

    public static void setPatientFields(Patient patient, HttpServletRequest req) {
        patient.setAddress(req.getParameter("address"));
        patient.setDateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth")));
    }


}
