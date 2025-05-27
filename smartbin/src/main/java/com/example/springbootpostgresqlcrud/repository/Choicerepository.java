package com.example.springbootpostgresqlcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springbootpostgresqlcrud.model.Choice;

public interface Choicerepository extends JpaRepository<Choice, Long> {

}
