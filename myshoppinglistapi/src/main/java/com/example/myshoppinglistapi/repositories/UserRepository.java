package com.example.myshoppinglistapi.repositories;

import com.example.myshoppinglistapi.entities.User;

import java.util.Optional;

public interface UserRepository {
    public Optional<User> findByEmailAndPassword(String email, String password);
}
