package ru.itis.medportal.repository;

import ru.itis.medportal.model.Specialization;
import ru.itis.medportal.util.DBConnection;
import ru.itis.medportal.util.SqlQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpecializationRepository {

    public List<Specialization> findAll() {
        Connection connection = DBConnection.getConnection();
        List<Specialization> specializations = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SqlQuery.SPECIALIZATION_FIND_ALL.get());
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                specializations.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specializations;
    }

    public Optional<Specialization> findById(Long id) {
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(SqlQuery.SPECIALIZATION_FIND_BY_ID.get());) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSet(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long save(Specialization specialization) {
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(SqlQuery.SPECIALIZATION_INSERT.get())) {
            stmt.setString(1, specialization.getName());
            stmt.setString(2, specialization.getDescription());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("spec_id");
            }
            throw new RuntimeException("Не удалось сохранить специализацию");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Specialization specialization) {
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(SqlQuery.SPECIALIZATION_UPDATE.get())) {
            stmt.setString(1, specialization.getName());
            stmt.setString(2, specialization.getDescription());
            stmt.setLong(3, specialization.getSpecId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long specId) {
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(SqlQuery.SPECIALIZATION_DELETE.get())) {
            stmt.setLong(1, specId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Specialization mapResultSet(ResultSet rs) throws SQLException {
        Specialization spec = new Specialization();
        spec.setSpecId(rs.getLong("spec_id"));
        spec.setName(rs.getString("name"));
        spec.setDescription(rs.getString("description"));
        return spec;
    }
}