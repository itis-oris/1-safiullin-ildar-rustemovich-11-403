package ru.itis.medportal.service;

import ru.itis.medportal.dto.DoctorDTO;
import ru.itis.medportal.model.Doctor;
import ru.itis.medportal.repository.AbstractUserRepository;
import ru.itis.medportal.util.ValidateUtil;

import java.util.ArrayList;

public class DoctorService extends AbstractUserService<Doctor, DoctorDTO> {
    public DoctorService(AbstractUserRepository<Doctor> repository) {
        super(repository);
    }

    @Override
    protected DoctorDTO createUserDTO(Doctor doctor) {
        try {
            DoctorDTO doctorDTO = new DoctorDTO();
            doctorDTO.setName(doctor.getName());
            doctorDTO.setSurname(doctor.getSurname());
            doctorDTO.setEmail(doctor.getEmail());
            doctorDTO.setId(doctor.getId());
            doctorDTO.setAboutMe(doctor.getAboutMe());
            doctorDTO.setOnlineConsultations(doctor.isOnlineConsultations());
            if (doctor.getSpecializations() != null) {
                doctorDTO.setSpecializations(new ArrayList<>(doctor.getSpecializations()));
            }
            return doctorDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void validateSpecificFields(Doctor user) {
        ValidateUtil.validateText(user.getAboutMe());
    }
}
