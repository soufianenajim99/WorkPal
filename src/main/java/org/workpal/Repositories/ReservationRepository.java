package org.workpal.Repositories;

import org.workpal.Models.Reservation;
import org.workpal.Repositories.RepositoryInterfaces.ReservationRepositoryInterface;
import org.workpal.Utils.JdbcConnection;
import java.util.ArrayList;
import java.util.List;

import org.workpal.Models.Espace;
import org.workpal.Models.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class ReservationRepository implements ReservationRepositoryInterface {
    private final MemberRepository memberRepo;
    private final EspaceRepository espaceRepo;

    public ReservationRepository(MemberRepository memberRepo, EspaceRepository espaceRepo) {
        this.memberRepo = memberRepo;
        this.espaceRepo = espaceRepo;
    }

    @Override
    public void save(Reservation reservation) {
        String sql = "INSERT INTO reservations (member_id, espace_id, reservation_date) VALUES (?, ?, ?)";
        try (Connection connection = JdbcConnection.getConnection().orElseThrow(() -> new SQLException("Failed to get database connection"));
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getMember().getId());
            statement.setInt(2, reservation.getEspace().getId());
            statement.setDate(3, java.sql.Date.valueOf(reservation.getReservationDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Reservation reservation) {
        String sql = "DELETE FROM reservations WHERE member_id = ? AND espace_id = ? AND reservation_date = ?";
        try (Connection connection = JdbcConnection.getConnection().orElseThrow(() -> new SQLException("Failed to get database connection"));
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getMember().getId());
            statement.setInt(2, reservation.getEspace().getId());
            statement.setDate(3, java.sql.Date.valueOf(reservation.getReservationDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Reservation> findByMemberAndEspace(int memberId, int espaceId) {
        String sql = "SELECT * FROM reservations WHERE member_id = ? AND espace_id = ?";
        try (Connection connection = JdbcConnection.getConnection().orElseThrow(() -> new SQLException("Failed to get database connection"));
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, memberId);
            statement.setInt(2, espaceId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {

                Optional<Member> optionalMember = memberRepo.findById(memberId);
                Member member = optionalMember.get();
                Espace espace = espaceRepo.findById(espaceId).orElseThrow(() -> new SQLException("Espace not found"));
                LocalDate reservationDate = rs.getDate("reservation_date").toLocalDate();
                return Optional.of(new Reservation(member, espace, reservationDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Reservation> findByMember(int memberId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE member_id = ?";
        try (Connection connection = JdbcConnection.getConnection().orElseThrow(() -> new SQLException("Failed to get database connection"));
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, memberId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Espace espace = espaceRepo.findById(rs.getInt("espace_id")).orElseThrow(() -> new SQLException("Espace not found"));
                LocalDate reservationDate = rs.getDate("reservation_date").toLocalDate();
                Optional<Member> optionalMember = memberRepo.findById(memberId);
                Member member = optionalMember.get();
                reservations.add(new Reservation(member, espace, reservationDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public List<Reservation> findByEspace(int espaceId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE espace_id = ?";
        try (Connection connection = JdbcConnection.getConnection().orElseThrow(() -> new SQLException("Failed to get database connection"));
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, espaceId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                Optional<Member> optionalMember = memberRepo.findById(rs.getInt("member_id"));
                Member member = optionalMember.get();
                LocalDate reservationDate = rs.getDate("reservation_date").toLocalDate();
                Espace espace = espaceRepo.findById(espaceId).orElseThrow(() -> new SQLException("Espace not found"));
                reservations.add(new Reservation(member, espace, reservationDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";
        try (Connection connection = JdbcConnection.getConnection().orElseThrow(() -> new SQLException("Failed to get database connection"));
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Optional<Member> optionalMember = memberRepo.findById(rs.getInt("member_id"));
                Member member = optionalMember.get();

                Espace espace = espaceRepo.findById(rs.getInt("espace_id")).orElseThrow(() -> new SQLException("Espace not found"));
                LocalDate reservationDate = rs.getDate("reservation_date").toLocalDate();
                reservations.add(new Reservation(member, espace, reservationDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
}
