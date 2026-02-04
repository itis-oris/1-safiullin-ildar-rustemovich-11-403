package ru.itis.medportal.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;
import ru.itis.medportal.dto.PatientDTO;
import ru.itis.medportal.dto.UserDTO;
import ru.itis.medportal.model.Appointment;
import ru.itis.medportal.model.Patient;
import ru.itis.medportal.model.User;
import ru.itis.medportal.repository.AppointmentRepository;
import ru.itis.medportal.util.ValidateUtil;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class AppointmentService {
    private AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public HashMap<Appointment, PatientDTO> getByDoctorForDoctor(long id) {
        Map<Appointment,Patient> map = appointmentRepository.findAppointmentsByDoctor(id,true);
        HashMap<Appointment,PatientDTO> appointments = new HashMap();
        for(Appointment appointment : map.keySet()) {
            if (map.get(appointment) != null) {
                PatientDTO patientDTO = new PatientDTO();
                patientDTO.setName(map.get(appointment).getName());
                patientDTO.setSurname(map.get(appointment).getSurname());
                patientDTO.setEmail(map.get(appointment).getEmail());
                appointments.put(appointment, patientDTO);
            } else {
                appointments.put(appointment, null);
            }
        }
        return appointments;
    }

    public List<Appointment> getByDoctorForPatient(long id) {
        return new ArrayList<>(appointmentRepository.findAppointmentsByDoctor(id, false)
                .keySet());
    }

    public void save(LocalDateTime date, long doctorId) {
        ValidateUtil.validateAppointmentDate(date);
        appointmentRepository.save(date,doctorId);
    }

    public void updateFromPatient(String appointmentId,String complaints, PatientDTO patientDTO) {
        ValidateUtil.validateText(complaints);
        Appointment appointment = new Appointment();
        appointment.setAppId(Long.valueOf(appointmentId));
        appointment.setStatus("запланирован");
        appointment.setComplaints(complaints);
        appointment.setPatientId(patientDTO.getId());
        appointmentRepository.updateFromPatient(appointment);
    }

    public void updateFromDoctor(Collection<Part> parts, boolean flag) throws IOException, ServletException {
        Appointment appointment = new Appointment();
        for (Part part : parts) {
            String partName = part.getName();
            if ("appointmentId".equals(partName)) {
                String appointmentId = readPartAsString(part);
                appointment.setAppId(Long.valueOf(appointmentId));
            } else if (flag && "recomFromDoc".equals(partName)) {
                String recomFromDoc = readPartAsString(part);
                ValidateUtil.validateText(recomFromDoc);
                appointment.setRecomFromDoc(recomFromDoc);
            } else if ("document".equals(partName) && part.getSize() > 0) {
                String fileName = part.getSubmittedFileName();
                if (fileName != null && !fileName.isEmpty()) {
                    ValidateUtil.validateFile(fileName);
                    String filePath = saveUploadedFile(part, fileName);
                    appointment.setDocumentPath(filePath);
                }
            }
        }
        appointmentRepository.updateFromDoctor(appointment, flag);
    }

    public <T extends UserDTO,R extends User> List<Map.Entry<Appointment, T>> getByUserId(long id, Class<T> dtoClass, boolean isDoctor) {
        HashMap<Appointment,R> map = appointmentRepository.getAppointmentsByUserId(id, isDoctor);
        HashMap<Appointment,T> appointments = new HashMap();
        for(Appointment appointment : map.keySet()) {
            try {
                T userDTO = dtoClass.getDeclaredConstructor().newInstance();
                userDTO.setName(map.get(appointment).getName());
                userDTO.setSurname(map.get(appointment).getSurname());
                appointments.put(appointment, userDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<Map.Entry<Appointment, T>> appointmentsList = new ArrayList<>(appointments.entrySet());
        appointmentsList.sort((entry1, entry2) ->
                entry2.getKey().getScheduledTime().compareTo(entry1.getKey().getScheduledTime())
        );
        return appointmentsList;
    }

    private String readPartAsString(Part part) throws IOException {
        try (InputStream inputStream = part.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.readLine();
        }
    }

    private String saveUploadedFile(Part part, String fileName) throws IOException {
        String uploadDir = "C:/uploads/medportal/";
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
        String filePath = uploadDir + uniqueFileName;
        part.write(filePath);
        return filePath;
    }
}
