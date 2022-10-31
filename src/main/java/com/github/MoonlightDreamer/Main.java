package com.github.MoonlightDreamer;

import com.github.MoonlightDreamer.model.User;
import org.postgresql.Driver;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                System.out.println(user);
            }

        } catch (SQLException e) {
            System.out.println("Failed. " + e.getMessage());
        }
    }
}
