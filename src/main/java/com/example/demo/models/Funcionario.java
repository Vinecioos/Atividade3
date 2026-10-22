package com.example.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity 
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne
    @JoinColumn(name="Setor_id")
    private Setor setor;
        @ManyToMany 
        @JoinTable(name = "Funcionario_Projeto", joinColumns={
            @JoinColumn(name="Funcionario_ID")}, inverseJoinColumns= {@JoinColumn (name= "Projeto_ID")})
        private List<Projeto> projetos;
        public Funcionario(String nome) {
            this.nome = nome;
        }
        public Funcionario() {
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
        public Setor getSetor() {
            return setor;
        }
        public void setSetor(Setor setor) {
            this.setor = setor;
        }
        public List<Projeto> getProjetos() {
            return projetos;
        }
        public void setProjetos(List<Projeto> projetos) {
            this.projetos = projetos;
        }
        @Override
        public String toString() {
            return "Funcionario [id=" + id + ", nome=" + nome + ", setor=" + setor + ", projetos=" + projetos + "]";
        }
    
}
