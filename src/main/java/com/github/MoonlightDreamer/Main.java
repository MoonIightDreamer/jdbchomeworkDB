package com.github.MoonlightDreamer;

import com.github.MoonlightDreamer.model.User;
import com.github.MoonlightDreamer.repository.JdbcUserRepository;
import org.postgresql.Driver;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        System.out.print("Enter 1 to GET, 2 to CREATE, 3 to DELETE: ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()) {
            JdbcUserRepository repository = new JdbcUserRepository(statement);
            switch (choice) {
                case "1":
                    System.out.print("Enter ID: ");
                    User user = repository.get(sc.nextInt());
                    if (user != null) System.out.println(user);
                    else System.out.println("No such user found!");
                    break;
                case "2":
                    System.out.print("Enter name: ");
                    if(repository.create(new User(null, sc.nextLine())))
                        System.out.println("Created successfully");
                case "3":
                    System.out.print("Enter ID: ");
                    if(repository.delete(sc.nextInt()))
                        System.out.println("Deleted successfully");
            }

        } catch (SQLException e) {
            System.out.println("Failed. " + e.getMessage());
        }
    }
}
