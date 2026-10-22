package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DadosProjetoDTO;
import com.example.demo.dto.FuncionarioDTO;
import com.example.demo.models.Funcionario;
import com.example.demo.models.Projeto;
import com.example.demo.models.Setor;
import com.example.demo.repositories.FuncionarioRepository;
import com.example.demo.repositories.SetorRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;

    public FuncionarioServiceImpl(
            FuncionarioRepository funcionarioRepository,
            SetorRepository setorRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
    }

    @Override
    public void adicionar(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario =
            new Funcionario(funcionarioDTO.getNome());

        if (funcionarioDTO.getIdSetor() != null) {
            Setor setor = setorRepository
                .findById(funcionarioDTO.getIdSetor())
                .orElseThrow(() ->
                    new RuntimeException("Setor não encontrado"));

            funcionario.setSetor(setor);
        }

        funcionario.setProjetos(new ArrayList<>());

        funcionarioRepository.save(funcionario);
    }

    @Override
    public List<DadosProjetoDTO> buscarProjetos(Long idFuncionario) {
        Funcionario funcionario = funcionarioRepository
            .findWithProjetosById(idFuncionario)
            .orElseThrow(() ->
                new RuntimeException("Funcionário não encontrado"));

        if (funcionario.getProjetos() == null) {
            return new ArrayList<>();
        }

        return funcionario.getProjetos()
            .stream()
            .map(this::converterParaDTO)
            .toList();
    }

    private DadosProjetoDTO converterParaDTO(Projeto projeto) {
        List<String> nomesFuncionarios = new ArrayList<>();

        if (projeto.getFuncionarios() != null) {
            nomesFuncionarios = projeto.getFuncionarios()
                .stream()
                .map(Funcionario::getNome)
                .toList();
        }

        return new DadosProjetoDTO(
            projeto.getId(),
            projeto.getDescricao(),
            projeto.getDataInicio(),
            projeto.getDataFim(),
            nomesFuncionarios
        );
    }
}