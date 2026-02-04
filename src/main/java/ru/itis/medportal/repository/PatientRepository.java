package ru.itis.medportal.repository;


import ru.itis.medportal.model.Patient;
import ru.itis.medportal.util.SqlQuery;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRepository extends AbstractUserRepository<Patient> {

    public PatientRepository() {
        super("patient", "patient_id");
    }

    @Override
    protected String getInsertSql() {
        return SqlQuery.PATIENT_INSERT.get();
    }

    @Override
    protected String getUpdateSql() {
        return SqlQuery.PATIENT_UPDATE.get();
    }

    @Override
    protected String getFindbyEmailSql() {
        return SqlQuery.PATIENT_FIND_BY_EMAIL.get();
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Patient patient) throws SQLException {
        stmt.setString(1, patient.getEmail());
        stmt.setString(2, patient.getPassword());
        stmt.setString(3, patient.getAddress());
        stmt.setString(4, patient.getName());
        stmt.setString(5, patient.getSurname());
        stmt.setDate(6, java.sql.Date.valueOf(patient.getDateOfBirth()));
    }

    @Override
    protected void setUpdateParameters(PreparedStatement stmt, Patient patient) throws SQLException {
        setInsertParameters(stmt, patient);
        stmt.setLong(7, patient.getId());
    }

    @Override
    protected Patient mapResultSet(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        setBaseFields(patient, rs);

        patient.setId(rs.getLong("patient_id"));
        patient.setAddress(rs.getString("address"));
        patient.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());

        return patient;
    }
}