package ru.itis.medportal.model;

import java.time.LocalDateTime;

public class Appointment {
    private Long appId;
    private Long doctorId;
    private Long patientId;
    private String complaints;
    private String documentPath;
    private String recomFromDoc;
    private LocalDateTime createdAt;
    private LocalDateTime scheduledTime;
    private String status;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {this.documentPath = documentPath;}

    public String getRecomFromDoc() {
        return recomFromDoc;
    }

    public void setRecomFromDoc(String recomFromDoc) {
        this.recomFromDoc = recomFromDoc;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
