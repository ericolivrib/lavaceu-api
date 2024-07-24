package com.erico.ceu.lavaceu.domain.permissao;

import com.erico.ceu.lavaceu.domain.usuario.Usuario;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Values valor;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    public enum Values {
        ADMIN, MORADOR, BOLSISTA, MAE
    }

    public Permissao() {
    }

    public Permissao(Values valor) {
        this.valor = valor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Values getValor() {
        return valor;
    }

    public void setValor(Values valor) {
        this.valor = valor;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
