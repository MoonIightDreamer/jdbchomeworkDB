package com.github.MoonlightDreamer.repository;

import com.github.MoonlightDreamer.model.User;

import java.sql.SQLException;

public interface UserRepository {

    User get(Integer id) throws SQLException;

    boolean delete(Integer id);

    boolean create(User user);
}
