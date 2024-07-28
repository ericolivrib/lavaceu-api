package com.erico.ceu.lavaceu.domain.agendamento;

import com.erico.ceu.lavaceu.domain.horario.HorarioDisponivel;
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
    @JoinColumn(name = "horario_disponivel_id")
    private HorarioDisponivel horarioDisponivel;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "lavadora_id")
    private Lavadora lavadora;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        DISPONIBILIZADO,    // Morador disponibilizou seu horário
        FUTURO,             // Agendamento futuro
        AUSENTE,            // Morador não compareceu
        ATRASADO,           // Morador está atrasado
        CONCLUIDO           // Morador lavou suas roupas
    }

    public Agendamento() {
    }

    public Agendamento(UUID id, HorarioDisponivel horarioDisponivel, Usuario usuario, Status status) {
        this.id = id;
        this.horarioDisponivel = horarioDisponivel;
        this.usuario = usuario;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public HorarioDisponivel getHorarioDisponivel() {
        return horarioDisponivel;
    }

    public void setHorarioDisponivel(HorarioDisponivel horario) {
        this.horarioDisponivel = horario;
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
