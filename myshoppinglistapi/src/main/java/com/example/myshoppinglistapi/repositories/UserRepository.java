package com.example.myshoppinglistapi.repositories;

import com.example.myshoppinglistapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findByEmailAndPassword(String email, String password);
}
