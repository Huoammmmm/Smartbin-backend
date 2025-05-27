package com.example.springbootpostgresqlcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springbootpostgresqlcrud.model.Question;

public interface Questionrepository extends JpaRepository<Question, Long> {}