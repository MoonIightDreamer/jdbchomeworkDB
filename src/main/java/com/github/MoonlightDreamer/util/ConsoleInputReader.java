package com.github.MoonlightDreamer.util;

import com.github.MoonlightDreamer.model.User;
import com.github.MoonlightDreamer.repository.UserRepository;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleInputReader {
    private static final Scanner sc = new Scanner(System.in);
    private UserRepository repository;
    private static final Logger log = LoggerFactory.getLogger(ConsoleInputReader.class);

    public ConsoleInputReader(UserRepository repository) {
        this.repository = repository;
    }

    public void proceedUserInput(){
        log.info("Enter 1 to GET, 2 to CREATE, 3 to DELETE: ");
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
        log.info("Enter ID: ");
        try {
            int id = sc.nextInt();
            Optional<User> user = repository.get(id);
            if (user.isPresent()) {
                log.info("Successfully got user = {}", user.get());
            }
            else {
                log.info("No user with id = {}", id);
            }
        } catch (SQLException e) {
            log.info(e.getMessage());
        } catch (InputMismatchException e) {
            log.info("ID must be an integer number.");
        }
    }

    private void create() {
        System.out.print("Enter name: ");
        try {
            User user = new User(null, sc.nextLine());
            if (repository.create(user)) {
                log.info("Created new user with id = {} and name = {}", user.getId(), user.getName());
            }
        } catch (SQLException e) {
            log.info(e.getMessage());
        }
    }

    private void delete() {
        try {
            System.out.print("Enter ID: ");
            if (repository.delete(sc.nextInt()))
                log.info("Deleted successfully");
        } catch (SQLException e) {
            log.info("Failed. " + e.getMessage());
        } catch (InputMismatchException e) {
            log.info("ID must be an integer number.");
        }
    }
}
