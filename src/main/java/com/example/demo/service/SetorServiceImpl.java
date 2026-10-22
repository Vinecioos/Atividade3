package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DadosSetorDTO;
import com.example.demo.dto.SetorDTO;
import com.example.demo.models.Funcionario;
import com.example.demo.models.Setor;
import com.example.demo.repositories.SetorRepository;

@Service
public class SetorServiceImpl implements SetorService {

    private final SetorRepository setorRepository;

    public SetorServiceImpl(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    @Override
    public void adicionar(SetorDTO setorDTO) {
        Setor setor = new Setor(setorDTO.getNome());

        setorRepository.save(setor);
    }

    @Override
    public DadosSetorDTO buscarSetorPorId(Long idSetor) {
        Setor setor = setorRepository
            .findWithFuncionarioById(idSetor)
            .orElseThrow(() ->
                new RuntimeException("Setor não encontrado"));

        List<String> nomesFuncionarios = new ArrayList<>();

        if (setor.getFuncionario() != null) {
            nomesFuncionarios = setor.getFuncionario()
                .stream()
                .map(Funcionario::getNome)
                .toList();
        }

        return new DadosSetorDTO(
            setor.getId(),
            setor.getNome(),
            nomesFuncionarios
        );
    }
}