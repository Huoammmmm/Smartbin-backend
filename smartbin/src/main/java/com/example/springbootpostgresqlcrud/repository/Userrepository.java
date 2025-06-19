package com.example.springbootpostgresqlcrud.repository;

import com.example.springbootpostgresqlcrud.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface Userrepository extends JpaRepository<User, Long> {
     Optional<User> findByEmail(String email);
     Optional<User> findByUsername(String username);
     boolean existsByUsername(String username);
     boolean existsByEmail(String email);
}