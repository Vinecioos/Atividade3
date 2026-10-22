package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.DadosProjetoDTO;
import com.example.demo.dto.ProjetoDTO;

public interface ProjetoService {

    void adicionar(ProjetoDTO projetoDTO);

    DadosProjetoDTO buscarProjetoPorId(Long id);

    List<DadosProjetoDTO> listarTodos();

    List<DadosProjetoDTO> buscarPorPeriodo(
        LocalDate inicio, LocalDate fim
    );

    void vincularFuncionario(Long idProjeto, Long idFuncionario);
}