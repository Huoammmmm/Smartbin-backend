package com.example.springbootpostgresqlcrud.repository;

import com.example.springbootpostgresqlcrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepository extends JpaRepository<User, Long> {
}