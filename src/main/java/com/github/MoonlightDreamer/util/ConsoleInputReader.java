package com.github.MoonlightDreamer.util;

import com.github.MoonlightDreamer.model.User;
import com.github.MoonlightDreamer.repository.UserRepository;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleInputReader {
    private static final Scanner sc = new Scanner(System.in);
    private UserRepository repository;

    public ConsoleInputReader(UserRepository repository) {
        this.repository = repository;
    }

    public void proceedUserInput(){
        System.out.print("Enter 1 to GET, 2 to CREATE, 3 to DELETE: ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                get();
                break;
            case "2":
                create();
                break;
            case "3":
                delete();
                break;
            default:
                System.out.println("Error occurred! Please, restart and enter 1, 2, or 3");
                break;
        }
    }

    private void get() {
        System.out.print("Enter ID: ");
        try {
            Optional<User> user = repository.get(sc.nextInt());
            if (user.isPresent()) System.out.println(user.get());
            else System.out.println("No such user found!");
        } catch (SQLException e) {
            System.out.println("Failed. " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("ID must be an integer number.");
        }
    }

    private void create() {
        System.out.print("Enter name: ");
        try {
            if (repository.create(new User(null, sc.nextLine())))
                System.out.println("Created successfully");
        } catch (SQLException e) {
            System.out.println("Failed. " + e.getMessage());
        }
    }

    private void delete() {
        try {
            System.out.print("Enter ID: ");
            if (repository.delete(sc.nextInt()))
                System.out.println("Deleted successfully");
        } catch (SQLException e) {
            System.out.println("Failed. " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("ID must be an integer number.");
        }
    }
}
