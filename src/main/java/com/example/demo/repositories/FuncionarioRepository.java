package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Funcionario;

public interface FuncionarioRepository
        extends JpaRepository<Funcionario, Long> {

    @EntityGraph(attributePaths = "projetos")
    Optional<Funcionario> findWithProjetosById(Long id);
}