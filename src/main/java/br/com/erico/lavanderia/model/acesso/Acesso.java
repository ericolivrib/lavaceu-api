package br.com.erico.lavanderia.model.acesso;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "acessos")
public class Acesso {

    @Id
    @Column(name = "acesso_id")
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "acesso")
    private List<AcessoUsuario> usuarios;

    public Acesso() {
    }

    public Acesso(Long id, String nome, List<AcessoUsuario> usuarios) {
        this.id = id;
        this.nome = nome;
        this.usuarios = usuarios;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Acesso acesso = (Acesso) o;
        return Objects.equals(id, acesso.id)
                && Objects.equals(nome, acesso.nome)
                && Objects.equals(usuarios, acesso.usuarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, usuarios);
    }

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
