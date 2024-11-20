package br.com.erico.lavanderia.model.acesso;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "acessos")
public class Acesso {

    @Id
    @Column(name = "acesso_id")
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "acesso")
    private List<AcessoUsuario> usuarios;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<AcessoUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<AcessoUsuario> usuarios) {
        this.usuarios = usuarios;
    }
}
