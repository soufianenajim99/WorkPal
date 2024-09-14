package org.workpal.Repositories;


import org.workpal.Models.User;
import org.workpal.Repositories.RepositoryInterfaces.UserRepositoryInterface;
import org.workpal.Utils.JdbcConnection;

import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository<T> implements UserRepositoryInterface {
    private  Class<T> type;
    public UserRepository(Class<T> type) {
        this.type = type;
    }
    @Override
    public T save(User user) {
        String tableName = type.getSimpleName().toLowerCase()+"s";
        String sql = "INSERT INTO "+tableName+" (username, password, email, address) VALUES (?, ?, ?, ?)";
        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error saving user: " + ex.getMessage());
        }
        T savedUser = (T) user;
        return savedUser;
    }

    public Optional<T> login(String email, String password) {
        String tableName = type.getSimpleName().toLowerCase() + "s"; // Dynamically determine table name
        String sql = "SELECT * FROM " + tableName + " WHERE email = ? AND password = ?";

        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Constructor<T> constructor = type.getConstructor(int.class, String.class, String.class, String.class, String.class);
                T user = constructor.newInstance(
                        rs.getInt("Id"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("Address")
                );

                return Optional.of(user);
            }
        } catch (SQLException | ReflectiveOperationException ex) {
            System.out.println("Error during login: " + ex.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<T> findById(int id) {
        String tableName = type.getSimpleName().toLowerCase() + "s"; // Dynamically determine table name
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Constructor<T> constructor = type.getConstructor(int.class, String.class, String.class, String.class, String.class);
                T user = constructor.newInstance(
                        rs.getInt("Id"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("Address")
                );

                return Optional.of(user);
            }
        } catch (SQLException | ReflectiveOperationException ex) {
            System.out.println("Error finding user by ID: " + ex.getMessage());
        }

        return Optional.empty();
    }


    @Override
    public List<T> findAll() {
        List<T> users = new ArrayList<>();
        String tableName = type.getSimpleName().toLowerCase() + "s"; // Dynamically determine table name
        String sql = "SELECT * FROM " + tableName;

        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            Constructor<T> constructor = type.getConstructor(int.class, String.class, String.class, String.class, String.class);
            while (rs.next()) {
                T user = constructor.newInstance(
                        rs.getInt("Id"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("Address")
                );
                users.add(user);
            }
        } catch (SQLException | ReflectiveOperationException ex) {
            System.out.println("Error finding all users: " + ex.getMessage());
        }
        return users;
    }


    @Override
    public void update(User user) {
        String tableName = type.getSimpleName().toLowerCase() + "s"; // Dynamically determine table name
        String sql = "UPDATE " + tableName + " SET Username = ?, Password = ?, Email = ?, Address = ? WHERE Id = ?";

        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress());
            pstmt.setInt(5, user.getId());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error updating " + type.getSimpleName().toLowerCase() + ": " + ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String tableName = type.getSimpleName().toLowerCase() + "s"; // Dynamically determine table name
        String sql = "DELETE FROM " + tableName + " WHERE Id = ?";

        try (Connection conn = JdbcConnection.getConnection().orElseThrow(SQLException::new);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error deleting " + type.getSimpleName().toLowerCase() + ": " + ex.getMessage());
        }
    }

}
