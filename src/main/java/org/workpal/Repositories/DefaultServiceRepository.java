package org.workpal.Repositories;
import org.workpal.Models.DefaultService;
import org.workpal.Models.Service;
import org.workpal.Utils.JdbcConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultServiceRepository extends ServiceRepository{
    @Override
    public void save(Service service) {
        if (!(service instanceof DefaultService)) {
            throw new IllegalArgumentException("Service must be an instance of DefaultService");
        }
        DefaultService defaultService = (DefaultService) service;
        String sql = "INSERT INTO default_services (name, description, price) VALUES (?, ?, ?) RETURNING id";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, defaultService.getName());
            pstmt.setString(2, defaultService.getDescription());
            pstmt.setDouble(3, defaultService.getPrice());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    defaultService.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error saving default service: " + ex.getMessage());
        }
    }

    @Override
    public void update(Service service) {
        if (!(service instanceof DefaultService)) {
            throw new IllegalArgumentException("Service must be an instance of DefaultService");
        }
        DefaultService defaultService = (DefaultService) service;
        String sql = "UPDATE default_services SET name = ?, description = ?, price = ? WHERE id = ?";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, defaultService.getName());
            pstmt.setString(2, defaultService.getDescription());
            pstmt.setDouble(3, defaultService.getPrice());
            pstmt.setInt(4, defaultService.getId());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error updating default service: " + ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM default_services WHERE id = ?";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error deleting default service: " + ex.getMessage());
        }
    }


    public Optional<DefaultService> findById(int id) {
        String sql = "SELECT * FROM default_services WHERE id = ?";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    DefaultService service = new DefaultService(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price")
                    );
                    return Optional.of(service);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error finding default service by ID: " + ex.getMessage());
        }
        return Optional.empty();
    }


    public List<DefaultService> findAll() {
        List<DefaultService> services = new ArrayList<>();
        String sql = "SELECT * FROM default_services";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DefaultService service = new DefaultService(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price")
                );
                services.add(service);
            }
        } catch (SQLException ex) {
            System.out.println("Error finding all default services: " + ex.getMessage());
        }
        return services;
    }
}
