package org.workpal.Repositories;
import org.workpal.Models.AdditionalService;
import org.workpal.Models.Service;
import org.workpal.Utils.JdbcConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class AdditionalServiceRepository extends ServiceRepository{
    @Override
    public void save(Service service) {
        if (!(service instanceof AdditionalService)) {
            throw new IllegalArgumentException("Service must be an instance of AdditionalService");
        }
        AdditionalService additionalService = (AdditionalService) service;
        String sql = "INSERT INTO additional_services (name, description, price) VALUES (?, ?, ?) RETURNING id";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, additionalService.getName());
            pstmt.setString(2, additionalService.getDescription());
            pstmt.setDouble(3, additionalService.getPrice());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    additionalService.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error saving additional service: " + ex.getMessage());
        }
    }

    @Override
    public void update(Service service) {
        if (!(service instanceof AdditionalService)) {
            throw new IllegalArgumentException("Service must be an instance of AdditionalService");
        }
        AdditionalService additionalService = (AdditionalService) service;
        String sql = "UPDATE additional_services SET name = ?, description = ?, price = ? WHERE id = ?";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, additionalService.getName());
            pstmt.setString(2, additionalService.getDescription());
            pstmt.setDouble(3, additionalService.getPrice());
            pstmt.setInt(4, additionalService.getId());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error updating additional service: " + ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM additional_services WHERE id = ?";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error deleting additional service: " + ex.getMessage());
        }
    }

    public Optional<AdditionalService> findById(int id) {
        String sql = "SELECT * FROM additional_services WHERE id = ?";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    AdditionalService service = new AdditionalService(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price")
                    );
                    return Optional.of(service);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error finding additional service by ID: " + ex.getMessage());
        }
        return Optional.empty();
    }


    public List<AdditionalService> findAll() {
        List<AdditionalService> services = new ArrayList<>();
        String sql = "SELECT * FROM additional_services";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                AdditionalService service = new AdditionalService(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price")
                );
                services.add(service);
            }
        } catch (SQLException ex) {
            System.out.println("Error finding all additional services: " + ex.getMessage());
        }
        return services;
    }
}
