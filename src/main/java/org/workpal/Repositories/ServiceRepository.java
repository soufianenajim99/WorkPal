package org.workpal.Repositories;

import org.workpal.Models.Service;
import org.workpal.Repositories.RepositoryInterfaces.ServiceRepositoryInterface;
import org.workpal.Utils.JdbcConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class ServiceRepository implements ServiceRepositoryInterface {
    @Override
    public void save(Service service) {
        String sql = "INSERT INTO services (name, description, price) VALUES (?, ?, ?) RETURNING id";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, service.getName());
            pstmt.setString(2, service.getDescription());
            pstmt.setDouble(3, service.getPrice());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    service.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error saving service: " + ex.getMessage());
        }
    }

    @Override
    public void update(Service service) {
        String sql = "UPDATE services SET name = ?, description = ?, price = ? WHERE id = ?";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, service.getName());
            pstmt.setString(2, service.getDescription());
            pstmt.setDouble(3, service.getPrice());
            pstmt.setInt(4, service.getId());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error updating service: " + ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM services WHERE id = ?";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error deleting service: " + ex.getMessage());
        }
    }




}
