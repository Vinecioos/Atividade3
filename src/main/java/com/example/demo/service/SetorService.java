package com.example.demo.service;

import com.example.demo.dto.DadosSetorDTO;
import com.example.demo.dto.SetorDTO;

public interface SetorService {

    void adicionar(SetorDTO setorDTO);

    DadosSetorDTO buscarSetorPorId(Long idSetor);
}