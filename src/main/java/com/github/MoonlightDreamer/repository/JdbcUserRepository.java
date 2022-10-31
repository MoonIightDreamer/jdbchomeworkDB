package com.github.MoonlightDreamer.repository;

import com.github.MoonlightDreamer.model.User;

public class JdbcUserRepository implements UserRepository{
    @Override
    public User get(Integer id) {
        return null;
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
