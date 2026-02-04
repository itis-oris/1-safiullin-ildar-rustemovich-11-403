package ru.itis.medportal.service;

import ru.itis.medportal.dto.PatientDTO;
import ru.itis.medportal.model.Patient;
import ru.itis.medportal.repository.AbstractUserRepository;
import ru.itis.medportal.util.ValidateUtil;


public class PatientService extends AbstractUserService<Patient, PatientDTO> {
    public PatientService(AbstractUserRepository<Patient> repository) {
        super(repository);
    }

    @Override
    protected PatientDTO createUserDTO(Patient patient) {
        try {
            PatientDTO patientDTO = new  PatientDTO();
            patientDTO.setName(patient.getName());
            patientDTO.setSurname(patient.getSurname());
            patientDTO.setEmail(patient.getEmail());
            patientDTO.setId(patient.getId());
            patientDTO.setDateOfBirth(patient.getDateOfBirth());
            patientDTO.setAddress(patient.getAddress());
            return patientDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void validateSpecificFields(Patient user) {
        ValidateUtil.validateBirthDate(user.getDateOfBirth());
        ValidateUtil.validateText(user.getAddress());
    }
}
