package com.github.MoonlightDreamer.repository;

import com.github.MoonlightDreamer.model.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserRepository {

    Optional<User> get(Integer id) throws SQLException;

    boolean delete(Integer id) throws SQLException;

    boolean create(User user) throws SQLException;
}
