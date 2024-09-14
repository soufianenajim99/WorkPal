package org.workpal.Repositories;

import org.workpal.Models.Category;
import org.workpal.Models.Espace;
import org.workpal.Models.Manager;
import org.workpal.Repositories.RepositoryInterfaces.EspaceRepositoryInterface;
import org.workpal.Utils.JdbcConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EspaceRepository implements EspaceRepositoryInterface {

    @Override
    public Espace save(Espace espace) {
        String sql = "INSERT INTO espace (name, location, category_id, manager_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, espace.getName());
            pstmt.setString(2, espace.getLocation());
            pstmt.setInt(3, espace.getCategory().getId()); // Assuming Category has a getId() method
            pstmt.setInt(4, espace.getManager().getId());   // Assuming Manager has a getId() method

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        espace.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error saving espace: " + ex.getMessage());
        }
        return espace;
    }

    @Override
    public Optional<Espace> findById(int id) {
        String sql = "SELECT * FROM espace WHERE id = ?";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int categoryId = rs.getInt("category_id");
                int managerId = rs.getInt("manager_id");

                // Fetch Category and Manager by ID
                Category category = fetchCategoryById(categoryId);
                Manager manager = fetchManagerById(managerId);

                Espace espace = new Espace(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        category,
                        manager
                );
                return Optional.of(espace);
            }
        } catch (SQLException ex) {
            System.out.println("Error finding espace by ID: " + ex.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Espace> findAll() {
        List<Espace> espaces = new ArrayList<>();
        String sql = "SELECT * FROM espace";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int categoryId = rs.getInt("category_id");
                int managerId = rs.getInt("manager_id");

                // Fetch Category and Manager by ID
                Category category = fetchCategoryById(categoryId);
                Manager manager = fetchManagerById(managerId);

                Espace espace = new Espace(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        category,
                        manager
                );
                espaces.add(espace);
            }
        } catch (SQLException ex) {
            System.out.println("Error finding all espaces: " + ex.getMessage());
        }
        return espaces;
    }

    @Override
    public void update(Espace espace) {
        String sql = "UPDATE espace SET name = ?, location = ?, category_id = ?, manager_id = ? WHERE id = ?";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, espace.getName());
            pstmt.setString(2, espace.getLocation());
            pstmt.setInt(3, espace.getCategory().getId()); // Assuming Category has a getId() method
            pstmt.setInt(4, espace.getManager().getId());   // Assuming Manager has a getId() method
            pstmt.setInt(5, espace.getId());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error updating espace: " + ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM espace WHERE id = ?";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error deleting espace: " + ex.getMessage());
        }
    }

    private Category fetchCategoryById(int id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching category by ID: " + ex.getMessage());
        }
        return new Category(); // Return a default or null object if not found
    }

    private Manager fetchManagerById(int id) {
        String sql = "SELECT * FROM managers WHERE id = ?";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Manager(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("address")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching manager by ID: " + ex.getMessage());
        }
        return new Manager(); // Return a default or null object if not found
    }
}
