package ru.itis.medportal.util;

public enum SqlQuery {
    APPOINTMENT_FIND_BY_DOCTOR_FOR_DOCTOR(
            "select * from appointment a " +
                    "left join patient p on a.patient_id=p.patient_id " +
                    "where doctor_id=? and (status = 'свободен' or status = 'запланирован') " +
                    "order by scheduled_time"
    ),

    APPOINTMENT_FIND_BY_DOCTOR_FOR_PATIENT (
            "select * from appointment " +
                    "where doctor_id = ? and status = 'свободен' " +
                    "order by scheduled_time"
    ),

    APPOINTMENT_ADD(
            "insert into appointment (scheduled_time, doctor_id) " +
                    "values (?, ?)"
    ),

    APPOINTMENT_UPDATE_FROM_PATIENT(
            "update appointment " +
                    "set patient_id = ?, status = ?, complaints = ? " +
                    "where app_id = ?"
    ),

    APPOINTMENT_UPDATE_FROM_DOCTOR_WITH_RECOM(
            "update appointment " +
                    "set document_path = ?, recom_from_doc = ?, status = 'проведён' " +
                    "where app_id = ?"
    ),

    APPOINTMENT_UPDATE_FROM_DOCTOR_WITHOUT_RECOM(
            "update appointment " +
                    "set document_path = ?, status = 'проведён' " +
                    "where app_id = ?"
    ),

    APPOINTMENT_GET_BY_PATIENT(
            "select * from appointment " +
                    "join doctor on appointment.doctor_id = doctor.doctor_id " +
                    "where patient_id = ? " +
                    "order by scheduled_time"
    ),

    APPOINTMENT_GET_BY_DOCTOR(
            "select * from appointment " +
                    "join patient on appointment.patient_id = patient.patient_id " +
                    "where doctor_id = ? and status = 'проведён' " +
                    "order by scheduled_time"
    ),

    SPECIALIZATION_FIND_ALL(
            "select * from specialization " +
                    "order by name"
    ),

    SPECIALIZATION_FIND_BY_ID(
            "select * from specialization " +
                    "where spec_id = ?"
    ),

    SPECIALIZATION_INSERT(
            "insert into specialization (name, description) " +
                    "values (?, ?) " +
                    "returning spec_id"
    ),

    SPECIALIZATION_UPDATE(
            "update specialization " +
                    "set name = ?, description = ? " +
                    "where spec_id = ?"
    ),

    SPECIALIZATION_DELETE(
            "delete from specialization " +
                    "where spec_id = ?"
    ),

    PATIENT_UPDATE(
            "update patient set email = ?, password = ?, address = ?, name = ?, surname = ?, date_of_birth = ? " +
                    "where patient_id = ?"
    ),

    PATIENT_INSERT(
            "insert into patient (email, password, address, name, surname, date_of_birth) " +
                    "values (?, ?, ?, ?, ?, ?) " +
                    "returning patient_id"
    ),

    PATIENT_FIND_BY_EMAIL(
            "select * from patient " +
                    "where email = ?"
    ),

    DOCTOR_UPDATE(
            "update doctor set email = ?, password = ?, name = ?, surname = ?, online_consultations = ?, about_me = ? " +
                    "where doctor_id = ?"
    ),

    DOCTOR_INSERT(
            "insert into doctor (email, password, name, surname, online_consultations, about_me) " +
                    "values (?, ?, ?, ?, ?, ?) " +
                    "returning doctor_id"
    ),

    DOCTOR_FIND_BY_EMAIL(
            "select * from doctor " +
                    "where email = ?"
    ),

    DOCTOR_SPECIALIZATION_DELETE_BY_DOCTOR(
            "delete from doctor_specialization " +
                    "where doctor_id = ?"
    ),

    DOCTOR_SPECIALIZATION_INSERT(
            "insert into doctor_specialization (doctor_id, spec_id) " +
                    "values (?, ?)"
    ),

    DOCTOR_SPECIALIZATION_FIND_BY_DOCTOR(
            "select s.spec_id, s.name, s.description " +
                    "from specialization s " +
                    "join doctor_specialization ds on s.spec_id = ds.spec_id " +
                    "where ds.doctor_id = ?"
    );

    private final String query;

    SqlQuery(String query) {
        this.query = query;
    }

    public String get() {
        return query;
    }
}