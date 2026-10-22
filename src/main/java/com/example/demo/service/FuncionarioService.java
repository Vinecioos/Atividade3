package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DadosProjetoDTO;
import com.example.demo.dto.FuncionarioDTO;

public interface FuncionarioService {

    void adicionar(FuncionarioDTO funcionarioDTO);

    List<DadosProjetoDTO> buscarProjetos(Long idFuncionario);
}