package com.github.MoonlightDreamer.repository;

import com.github.MoonlightDreamer.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class JdbcUserRepository implements UserRepository {
    private final Connection connection;

    public JdbcUserRepository(Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }

    @Override
    public Optional<User> get(Integer id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT id, name FROM users WHERE id=?")) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    return Optional.of(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean create(User user) throws SQLException {
        if (!user.isNew()) {
            return false;
        }
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO users(name) VALUES(?) RETURNING id")) {
            ps.setString(1, user.getName());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                user.setId(id);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
