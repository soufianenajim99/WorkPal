package org.workpal.Repositories;

import org.workpal.Models.Reservation;
import org.workpal.Repositories.RepositoryInterfaces.ReservationRepositoryInterface;
import org.workpal.Utils.JdbcConnection;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ReservationRepository implements ReservationRepositoryInterface {
    @Override
    public void save(Reservation reservation) {
        String sql = "INSERT INTO reservation (member_id, espace_id, reservation_date) VALUES (?, ?, ?)";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getMemberId());
            pstmt.setInt(2, reservation.getEspaceId());
            pstmt.setDate(3, Date.valueOf(reservation.getReservationDate())); // Convert LocalDate to SQL Date
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error saving reservation: " + ex.getMessage());
        }
    }

    @Override
    public List<Reservation> findByMemberId(int memberId) {
        String sql = "SELECT * FROM reservation WHERE member_id = ?";
        List<Reservation> reservations = new ArrayList<>();
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("member_id"),
                        rs.getInt("espace_id"),
                        rs.getDate("reservation_date").toLocalDate() // Convert SQL Date to LocalDate
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error finding reservations by member ID: " + ex.getMessage());
        }
        return reservations;
    }

    @Override
    public List<Reservation> findByEspaceId(int espaceId) {
        String sql = "SELECT * FROM reservation WHERE espace_id = ?";
        List<Reservation> reservations = new ArrayList<>();
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, espaceId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("member_id"),
                        rs.getInt("espace_id"),
                        rs.getDate("reservation_date").toLocalDate() // Convert SQL Date to LocalDate
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error finding reservations by espace ID: " + ex.getMessage());
        }
        return reservations;
    }

    @Override
    public void delete(int memberId, int espaceId) {
        String sql = "DELETE FROM reservation WHERE member_id = ? AND espace_id = ?";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberId);
            pstmt.setInt(2, espaceId);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error deleting reservation: " + ex.getMessage());
        }
    }
}
