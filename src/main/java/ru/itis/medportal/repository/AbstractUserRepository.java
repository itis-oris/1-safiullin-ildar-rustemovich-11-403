package ru.itis.medportal.repository;


import ru.itis.medportal.model.Doctor;
import ru.itis.medportal.model.Specialization;
import ru.itis.medportal.model.User;
import ru.itis.medportal.util.DBConnection;
import ru.itis.medportal.util.SqlQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractUserRepository<T extends User> {

    private final String tableName;
    private final String idColumnName;

    protected AbstractUserRepository(String tableName, String idColumnName) {
        this.tableName = tableName;
        this.idColumnName = idColumnName;
    }

    protected abstract String getInsertSql();
    protected abstract String getUpdateSql();
    protected abstract String getFindbyEmailSql();
    protected abstract void setInsertParameters(PreparedStatement stmt, T entity) throws SQLException;
    protected abstract void setUpdateParameters(PreparedStatement stmt, T entity) throws SQLException;
    protected abstract T mapResultSet(ResultSet rs) throws SQLException;

    public Long save(T entity) {
        Connection connection = DBConnection.getConnection();
        String sql = getInsertSql();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            setInsertParameters(stmt, entity);
            ResultSet resultSet = stmt.executeQuery();

            if (!resultSet.next()) {
                throw new RuntimeException("INSERT не вернул ID");
            }
            Long savedId = resultSet.getLong(idColumnName);

            if (entity instanceof Doctor doctor) {
                saveDoctorSpecializations(connection, savedId, doctor.getSpecializations());
            }

            return savedId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean checkEmail(String email) {
        Connection connection = DBConnection.getConnection();
        String sql = getFindbyEmailSql();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultSet.close();
                return true;
            } else {
                resultSet.close();
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> findAll() {
        Connection connection = DBConnection.getConnection();
        List<T> entities = new ArrayList<>();
        String sql = "select * from " + tableName;

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                T entity = mapResultSet(rs);

                if (entity instanceof Doctor doctor) {
                    loadDoctorSpecializations(connection, doctor);
                }

                entities.add(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    public void update(T entity) {
        Connection connection = DBConnection.getConnection();
        String sql = getUpdateSql();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            setUpdateParameters(stmt, entity);
            stmt.executeUpdate();

            if (entity instanceof Doctor doctor) {
                updateDoctorSpecializations(connection, doctor);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<T> checkByEmailAndPassword(String email) {
        Connection connection = DBConnection.getConnection();
        String sql = "select * from " + tableName + " where email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                T entity = mapResultSet(rs);
                if (entity instanceof Doctor doctor) {
                    loadDoctorSpecializations(connection, doctor);
                }
                rs.close();
                return Optional.of(entity);
            } else {
                rs.close();
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void setBaseFields(T entity, ResultSet rs) throws SQLException {
        entity.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        entity.setEmail(rs.getString("email"));
        entity.setPassword(rs.getString("password"));
        entity.setName(rs.getString("name"));
        entity.setSurname(rs.getString("surname"));
    }

    private void updateDoctorSpecializations(Connection connection, Doctor doctor) throws SQLException {
        String deleteSql = SqlQuery.DOCTOR_SPECIALIZATION_DELETE_BY_DOCTOR.get();
        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
            deleteStmt.setLong(1, doctor.getId());
            deleteStmt.executeUpdate();
        }

        if (doctor.getSpecializations() != null && !doctor.getSpecializations().isEmpty()) {
            String insertSql = SqlQuery.DOCTOR_SPECIALIZATION_INSERT.get();
            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                for (Specialization spec : doctor.getSpecializations()) {
                    insertStmt.setLong(1, doctor.getId());
                    insertStmt.setLong(2, spec.getSpecId());
                    insertStmt.addBatch();
                }
                insertStmt.executeBatch();
            }
        }
    }

    private void loadDoctorSpecializations(Connection connection, Doctor doctor) throws SQLException {
        String sql = SqlQuery.DOCTOR_SPECIALIZATION_FIND_BY_DOCTOR.get();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, doctor.getId());
            ResultSet rs = stmt.executeQuery();

            List<Specialization> specializations = new ArrayList<>();
            while (rs.next()) {
                Specialization spec = new Specialization();
                spec.setSpecId(rs.getLong("spec_id"));
                spec.setName(rs.getString("name"));
                spec.setDescription(rs.getString("description"));
                specializations.add(spec);
            }
            doctor.setSpecializations(specializations);
        }
    }

    private void saveDoctorSpecializations(Connection connection, Long doctorId, List<Specialization> specializations) throws SQLException {
        if (specializations == null || specializations.isEmpty()) return;

        String sql = SqlQuery.DOCTOR_SPECIALIZATION_INSERT.get();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Specialization spec : specializations) {
                stmt.setLong(1, doctorId);
                stmt.setLong(2, spec.getSpecId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }
}