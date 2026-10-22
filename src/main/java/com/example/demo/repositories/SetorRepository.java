package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Setor;

public interface SetorRepository extends JpaRepository<Setor, Long> {

    @EntityGraph(attributePaths = "funcionario")
    Optional<Setor> findWithFuncionarioById(Long id);
}