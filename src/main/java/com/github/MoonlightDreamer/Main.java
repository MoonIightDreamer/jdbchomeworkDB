package com.github.MoonlightDreamer;

import com.github.MoonlightDreamer.model.User;
import com.github.MoonlightDreamer.repository.JdbcUserRepository;
import org.postgresql.Driver;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()) {
            JdbcUserRepository repository = new JdbcUserRepository(connection, statement);
            System.out.println(repository.get(100001));
        } catch (SQLException e) {
            System.out.println("Failed. " + e.getMessage());
        }
    }
}
