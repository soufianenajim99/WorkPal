package org.workpal.Repositories;

import org.workpal.Models.Category;
import org.workpal.Models.Espace;
import org.workpal.Models.Event;
import org.workpal.Models.Manager;
import org.workpal.Repositories.RepositoryInterfaces.EventRepositoryInterface;
import org.workpal.Utils.JdbcConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class EventRepository implements EventRepositoryInterface {
    EspaceRepository espaceRepo = new EspaceRepository();
    @Override
    public Event save(Event event) {
        String sql = "INSERT INTO events (name, description, date, start_time, end_time, espace_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = JdbcConnection.getConnection().orElseThrow(() -> new SQLException("Failed to get database connection"));
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, event.getName());
            statement.setString(2, event.getDescription());
            statement.setDate(3, Date.valueOf(event.getDate()));
            statement.setTime(4, Time.valueOf(event.getStartTime()));
            statement.setTime(5, Time.valueOf(event.getEndTime()));
            statement.setInt(6, event.getEspace().getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        event.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public Optional<Event> findById(int id) {
        String sql = "SELECT * FROM events WHERE id = ?";

        try (Connection connection = JdbcConnection.getConnection().orElseThrow(() -> new SQLException("Failed to get database connection"));
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int espaceId = rs.getInt("espace_id");
                Optional<Espace> espaceOptional = espaceRepo.findById(espaceId);
                Espace espace = espaceOptional.get();
                return Optional.of(new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("start_time").toLocalTime(),
                        rs.getTime("end_time").toLocalTime(),
                        espace
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Event> findAll() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events";
        try (Connection connection = JdbcConnection.getConnection().orElseThrow(() -> new SQLException("Failed to get database connection"));
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            int espaceId = rs.getInt("espace_id");
            Optional<Espace> espaceOptional = espaceRepo.findById(espaceId);
            Espace espace = espaceOptional.get();
            while (rs.next()) {
                events.add(new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("start_time").toLocalTime(),
                        rs.getTime("end_time").toLocalTime(),
                        espace
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public void update(Event event) {
        String sql = "UPDATE events SET name = ?, description = ?, date = ?, start_time = ?, end_time = ?, espace_id = ? WHERE id = ?";
        try (Connection connection = JdbcConnection.getConnection().orElseThrow(() -> new SQLException("Failed to get database connection"));
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, event.getName());
            statement.setString(2, event.getDescription());
            statement.setDate(3, Date.valueOf(event.getDate()));
            statement.setTime(4, Time.valueOf(event.getStartTime()));
            statement.setTime(5, Time.valueOf(event.getEndTime()));
            statement.setInt(6, event.getEspace().getId());
            statement.setInt(7, event.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM events WHERE id = ?";
        try (Connection connection = JdbcConnection.getConnection().orElseThrow(() -> new SQLException("Failed to get database connection"));
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
