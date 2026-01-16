package ru.itis.medportal.repository;

import ru.itis.medportal.model.Doctor;
import ru.itis.medportal.util.SqlQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorRepository extends AbstractUserRepository<Doctor> {

    public DoctorRepository() {
        super("doctor", "doctor_id");
    }

    @Override
    protected String getInsertSql() {
        return SqlQuery.DOCTOR_INSERT.get();
    }

    @Override
    protected String getUpdateSql() {
        return SqlQuery.DOCTOR_UPDATE.get();
    }

    @Override
    protected String getFindbyEmailSql() {
        return SqlQuery.DOCTOR_FIND_BY_EMAIL.get();
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Doctor doctor) throws SQLException {
        stmt.setString(1, doctor.getEmail());
        stmt.setString(2, doctor.getPassword());
        stmt.setString(3, doctor.getName());
        stmt.setString(4, doctor.getSurname());
        stmt.setBoolean(5, doctor.isOnlineConsultations());
        stmt.setString(6, doctor.getAboutMe());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement stmt, Doctor doctor) throws SQLException {
        setInsertParameters(stmt, doctor);
        stmt.setLong(7, doctor.getId());
    }

    @Override
    protected Doctor mapResultSet(ResultSet rs) throws SQLException {
        Doctor doctor = new Doctor();
        setBaseFields(doctor, rs);

        doctor.setId(rs.getLong("doctor_id"));
        doctor.setOnlineConsultations(rs.getBoolean("online_consultations"));
        doctor.setAboutMe(rs.getString("about_me"));

        return doctor;
    }
}
