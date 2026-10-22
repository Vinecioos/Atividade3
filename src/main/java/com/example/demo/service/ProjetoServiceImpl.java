package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DadosProjetoDTO;
import com.example.demo.dto.ProjetoDTO;
import com.example.demo.models.Funcionario;
import com.example.demo.models.Projeto;
import com.example.demo.repositories.FuncionarioRepository;
import com.example.demo.repositories.ProjetoRepository;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ProjetoServiceImpl(
            ProjetoRepository projetoRepository,
            FuncionarioRepository funcionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public void adicionar(ProjetoDTO projetoDTO) {
        Projeto projeto = new Projeto(
            projetoDTO.getDescricao(),
            projetoDTO.getDataInicio(),
            projetoDTO.getDataFim()
        );

        projetoRepository.save(projeto);
    }

    @Override
    public DadosProjetoDTO buscarProjetoPorId(Long id) {
        Projeto projeto = projetoRepository
            .findWithFuncionariosById(id)
            .orElseThrow(() ->
                new RuntimeException("Projeto não encontrado"));

        return converterParaDTO(projeto);
    }

    @Override
    public List<DadosProjetoDTO> listarTodos() {
        return projetoRepository.findAll()
            .stream()
            .map(this::converterParaDTO)
            .toList();
    }

    @Override
    public List<DadosProjetoDTO> buscarPorPeriodo(
            LocalDate inicio, LocalDate fim) {
        return projetoRepository
            .findByDataInicioBetween(inicio, fim)
            .stream()
            .map(this::converterParaDTO)
            .toList();
    }

    @Override
    public void vincularFuncionario(
            Long idProjeto, Long idFuncionario) {

        Projeto projeto = projetoRepository.findById(idProjeto)
            .orElseThrow(() ->
                new RuntimeException("Projeto não encontrado"));

        Funcionario funcionario = funcionarioRepository
            .findById(idFuncionario)
            .orElseThrow(() ->
                new RuntimeException("Funcionário não encontrado"));

        if (funcionario.getProjetos() == null) {
            funcionario.setProjetos(new ArrayList<>());
        }

        boolean jaVinculado = funcionario.getProjetos()
            .stream()
            .anyMatch(p -> p.getId().equals(idProjeto));

        if (!jaVinculado) {
            funcionario.getProjetos().add(projeto);
            funcionarioRepository.save(funcionario);
        }
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