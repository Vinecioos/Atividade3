package com.example.demo.dto;

import java.util.List;

public class DadosSetorDTO {

    private Long id;
    private String nome;
    private List<String> funcionarios;

    public DadosSetorDTO() {
    }

    public DadosSetorDTO(Long id, String nome,
                         List<String> funcionarios) {
        this.id = id;
        this.nome = nome;
        this.funcionarios = funcionarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<String> funcionarios) {
        this.funcionarios = funcionarios;
    }
}