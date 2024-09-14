package org.workpal.Repositories;
import org.workpal.Models.Subscription;
import org.workpal.Utils.JdbcConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.workpal.Repositories.RepositoryInterfaces.SubscriptionRepositoryInterface;

public class SubscriptionRepository implements SubscriptionRepositoryInterface {
    @Override
    public void save(Subscription subscription) {
        String sql = "INSERT INTO subscriptions (name, description, price, duration) VALUES (?, ?, ?, ?)";

        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, subscription.getName());
            pstmt.setString(2, subscription.getDescription());
            pstmt.setDouble(3, subscription.getPrice());
            pstmt.setString(4, subscription.getDuration());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        subscription.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error saving subscription: " + ex.getMessage());
        }
    }

    @Override
    public void update(Subscription subscription) {
        String sql = "UPDATE subscriptions SET name = ?, description = ?, price = ?, duration = ? WHERE id = ?";

        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subscription.getName());
            pstmt.setString(2, subscription.getDescription());
            pstmt.setDouble(3, subscription.getPrice());
            pstmt.setString(4, subscription.getDuration());
            pstmt.setInt(5, subscription.getId());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error updating subscription: " + ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM subscriptions WHERE id = ?";

        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error deleting subscription: " + ex.getMessage());
        }
    }

    @Override
    public Optional<Subscription> findById(int id) {
        String sql = "SELECT * FROM subscriptions WHERE id = ?";

        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Subscription subscription = new Subscription(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("duration")
                );
                return Optional.of(subscription);
            }
        } catch (SQLException ex) {
            System.out.println("Error finding subscription by ID: " + ex.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Subscription> findAll() {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM subscriptions";

        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Subscription subscription = new Subscription(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("duration")
                );
                subscriptions.add(subscription);
            }
        } catch (SQLException ex) {
            System.out.println("Error finding all subscriptions: " + ex.getMessage());
        }

        return subscriptions;
    }
}
