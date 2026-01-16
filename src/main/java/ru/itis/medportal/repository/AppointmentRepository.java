package ru.itis.medportal.repository;

import ru.itis.medportal.model.Appointment;
import ru.itis.medportal.model.Doctor;
import ru.itis.medportal.model.Patient;
import ru.itis.medportal.util.DBConnection;
import ru.itis.medportal.util.SqlQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AppointmentRepository {

    public <T> Map<Appointment, T> findAppointmentsByDoctor(long doctorId, boolean forDoctor) {
        Connection connection = DBConnection.getConnection();
        Map<Appointment, T> appointments = new HashMap<>();

        String sql = forDoctor ? SqlQuery.APPOINTMENT_FIND_BY_DOCTOR_FOR_DOCTOR.get() : SqlQuery.APPOINTMENT_FIND_BY_DOCTOR_FOR_PATIENT.get();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Appointment appointment = new Appointment();

                if (forDoctor) {
                    setAppointment(appointment, resultSet);
                    T patient = null;
                    if (resultSet.getObject("patient_id") != null) {
                        Patient p = new Patient();
                        p.setName(resultSet.getString("name"));
                        p.setSurname(resultSet.getString("surname"));
                        p.setEmail(resultSet.getString("email"));
                        patient = (T) p;
                    }
                    appointments.put(appointment, patient);
                } else {
                    appointment.setScheduledTime(resultSet.getTimestamp("scheduled_time").toLocalDateTime());
                    appointment.setAppId(resultSet.getLong("app_id"));
                    appointments.put(appointment, null);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointments;
    }

    public void save(LocalDateTime date, long doctorId) {
        Connection connection = DBConnection.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.APPOINTMENT_ADD.get())) {
            preparedStatement.setTimestamp(1,java.sql.Timestamp.valueOf(date));
            preparedStatement.setLong(2,doctorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFromPatient(Appointment appointment) {
        Connection connection = DBConnection.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.APPOINTMENT_UPDATE_FROM_PATIENT.get())) {
            preparedStatement.setLong(1,appointment.getPatientId());
            preparedStatement.setString(2,appointment.getStatus());
            preparedStatement.setString(3,appointment.getComplaints());
            preparedStatement.setLong(4,appointment.getAppId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFromDoctor(Appointment appointment,boolean flag) {
        Connection connection = DBConnection.getConnection();
        String sql;
        if (flag) sql = SqlQuery.APPOINTMENT_UPDATE_FROM_DOCTOR_WITH_RECOM.get();
        else sql = SqlQuery.APPOINTMENT_UPDATE_FROM_DOCTOR_WITHOUT_RECOM.get();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,appointment.getDocumentPath());
            if (flag) {
                preparedStatement.setString(2,appointment.getRecomFromDoc());
                preparedStatement.setLong(3,appointment.getAppId());
            } else preparedStatement.setLong(2,appointment.getAppId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> HashMap<Appointment, T> getAppointmentsByUserId(long id, boolean isDoctor) {
        Connection connection = DBConnection.getConnection();
        HashMap<Appointment, T> appointments = new HashMap<>();

        String sql = isDoctor ? SqlQuery.APPOINTMENT_GET_BY_DOCTOR.get() : SqlQuery.APPOINTMENT_GET_BY_PATIENT.get();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Appointment appointment = new Appointment();
                setAppointment(appointment, resultSet);
                T person;
                if (isDoctor) {
                    Patient patient = new Patient();
                    patient.setName(resultSet.getString("name"));
                    patient.setSurname(resultSet.getString("surname"));
                    person = (T) patient;
                } else {
                    Doctor doctor = new Doctor();
                    doctor.setName(resultSet.getString("name"));
                    doctor.setSurname(resultSet.getString("surname"));
                    person = (T) doctor;
                }
                appointments.put(appointment, person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return appointments;
    }

    private void setAppointment(Appointment appointment,ResultSet resultSet) throws SQLException {
        appointment.setAppId(resultSet.getLong("app_id"));
        appointment.setPatientId(resultSet.getLong("patient_id"));
        appointment.setDoctorId(resultSet.getLong("doctor_id"));
        appointment.setComplaints(resultSet.getString("complaints"));
        appointment.setStatus(resultSet.getString("status"));
        appointment.setScheduledTime(resultSet.getTimestamp("scheduled_time").toLocalDateTime());
        appointment.setDocumentPath(resultSet.getString("document_path"));
        appointment.setRecomFromDoc(resultSet.getString("recom_from_doc"));
    }
}
