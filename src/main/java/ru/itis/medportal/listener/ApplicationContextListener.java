package ru.itis.medportal.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.itis.medportal.repository.AppointmentRepository;
import ru.itis.medportal.repository.DoctorRepository;
import ru.itis.medportal.repository.PatientRepository;
import ru.itis.medportal.repository.SpecializationRepository;
import ru.itis.medportal.service.*;
import ru.itis.medportal.util.DBConnection;


@WebListener
public class ApplicationContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("org.postgresql.Driver");
            DBConnection.getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        DoctorRepository doctorRepository = new DoctorRepository();
        PatientRepository patientRepository = new PatientRepository();
        AppointmentRepository appointmentRepository = new AppointmentRepository();
        SpecializationRepository specializationRepository = new SpecializationRepository();

        DoctorService doctorService = new DoctorService(doctorRepository);
        PatientService patientService = new PatientService(patientRepository);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);
        SpecializationService specializationService = new SpecializationService(specializationRepository);

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("doctorService", doctorService);
        servletContext.setAttribute("patientService", patientService);
        servletContext.setAttribute("appointmentService", appointmentService);
        servletContext.setAttribute("specializationService", specializationService);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        DBConnection.releaseConnection();
    }

}
