package com.example.demo.auth;

import java.util.Optional;

public interface UserDao {
    public Optional<User> selectUserByUsername(String username);
}
