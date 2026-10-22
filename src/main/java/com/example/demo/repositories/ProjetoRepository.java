package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @EntityGraph(attributePaths = "funcionarios")
    Optional<Projeto> findWithFuncionariosById(Long id);

    List<Projeto> findByDataInicioBetween(
        LocalDate inicio, LocalDate fim
    );
}