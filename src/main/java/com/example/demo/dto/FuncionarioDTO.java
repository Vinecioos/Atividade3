package com.example.demo.dto;

public class FuncionarioDTO {

    private Long id;
    private String nome;
    private Long idSetor;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Long id, String nome, Long idSetor) {
        this.id = id;
        this.nome = nome;
        this.idSetor = idSetor;
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

    public Long getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Long idSetor) {
        this.idSetor = idSetor;
    }
}