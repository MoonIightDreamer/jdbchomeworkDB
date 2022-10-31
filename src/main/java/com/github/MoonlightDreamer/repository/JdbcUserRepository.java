package com.github.MoonlightDreamer.repository;

import com.github.MoonlightDreamer.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUserRepository implements UserRepository{

    private Connection connection;

    private Statement statement;

    public JdbcUserRepository(Connection connection, Statement statement) {
        this.connection = connection;
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
            throw new RuntimeException("No such user found!");
        }
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(User user) {
        return false;
    }
}
