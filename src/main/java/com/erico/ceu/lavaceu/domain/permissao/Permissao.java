package com.erico.ceu.lavaceu.domain.permissao;

import com.erico.ceu.lavaceu.domain.usuario.Usuario;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Permissao {

    @Id
    private Integer id;

    private String valor;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    public enum Values {
        ADMIN, MORADOR, BOLSISTA, MAE
    }

    public Permissao() {
    }

    public Permissao(Values valor) {
        this.id = valor.ordinal();
        this.valor = valor.name();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
