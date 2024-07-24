package com.erico.ceu.lavaceu.domain.usuario;

import com.erico.ceu.lavaceu.domain.agendamento.Agendamento;
import com.erico.ceu.lavaceu.domain.permissao.Permissao;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String email;

    private String matricula;

    private String telefone;

    private Integer apartamento;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos;

    @ManyToOne
    private Permissao permissao;

    public Usuario() {
    }

    public Usuario(UUID id, String nome, String email, String matricula, String telefone, Integer apartamento, Permissao permissao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.telefone = telefone;
        this.apartamento = apartamento;
        this.permissao = permissao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getApartamento() {
        return apartamento;
    }

    public void setApartamento(Integer apartamento) {
        this.apartamento = apartamento;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }
}
