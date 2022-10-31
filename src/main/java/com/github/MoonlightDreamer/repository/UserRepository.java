package com.github.MoonlightDreamer.repository;

import com.github.MoonlightDreamer.model.User;

public interface UserRepository {

    User get(Integer id);

    boolean delete(Integer id);

    boolean create(User user);
}
