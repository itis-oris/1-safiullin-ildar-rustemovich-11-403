package ru.itis.medportal.dto;


import ru.itis.medportal.model.Specialization;

import java.util.List;

public class DoctorDTO extends UserDTO {
    private boolean onlineConsultations;
    private String aboutMe;
    private List<Specialization> specializations;

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }

    public boolean isOnlineConsultations() {
        return onlineConsultations;
    }

    public void setOnlineConsultations(boolean onlineConsultations) {
        this.onlineConsultations = onlineConsultations;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }


}
