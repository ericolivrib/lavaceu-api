package com.erico.ceu.lavaceu.domain.agendamento;

import com.erico.ceu.lavaceu.domain.horario.Horario;
import com.erico.ceu.lavaceu.domain.lavadora.Lavadora;
import com.erico.ceu.lavaceu.domain.usuario.Usuario;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Horario horario;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Lavadora lavadora;

    private Status status;

    public enum Status {
        DISPONIBILIZADO,
        FUTURO,
        AUSENTE,
        ATRASADO,
        CONCLUIDO,
        CANCELADO
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Usuario getMorador() {
        return usuario;
    }

    public void setMorador(Usuario usuario) {
        this.usuario = usuario;
    }

    public Lavadora getMaquina() {
        return lavadora;
    }

    public void setMaquina(Lavadora lavadora) {
        this.lavadora = lavadora;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
