package com.github.MoonlightDreamer;

import com.github.MoonlightDreamer.repository.JdbcUserRepository;
import com.github.MoonlightDreamer.util.ConsoleInputReader;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            JdbcUserRepository repository = new JdbcUserRepository(connection);
            ConsoleInputReader c = new ConsoleInputReader(repository);
            c.proceedUserInput();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
