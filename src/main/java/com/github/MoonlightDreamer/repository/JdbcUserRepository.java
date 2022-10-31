package com.github.MoonlightDreamer.repository;

import com.github.MoonlightDreamer.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUserRepository implements UserRepository{

    private Statement statement;

    public JdbcUserRepository(Statement statement) {
        this.statement = statement;
    }

    @Override
    public User get(Integer id) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE id=" + id);
        if(rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        statement.executeUpdate("DELETE FROM users WHERE id=" + id);
        return get(id) == null;
    }

    @Override
    public boolean create(User user) throws SQLException {
        if(user.isNew()) {
            statement.executeUpdate("INSERT INTO users(name) VALUES('" + user.getName() + "')");
        }
        return user.isNew();
    }
}
