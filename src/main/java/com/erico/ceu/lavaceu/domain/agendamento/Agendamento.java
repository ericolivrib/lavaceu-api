package com.erico.ceu.lavaceu.domain.agendamento;

import com.erico.ceu.lavaceu.domain.horario.HorarioLiberado;
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
    @JoinColumn(name = "horario_liberado_id")
    private HorarioLiberado horarioLiberado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "lavadora_id")
    private Lavadora lavadora;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        DISPONIBILIZADO("Disponibilizado"), // Morador disponibilizou seu horário
        AGENDADO("Agendado"),               // Agendamento futuro
        AUSENTE("Não comparecido"),         // Morador não compareceu
        ATRASADO("Usuário atrasado"),       // Morador está atrasado
        CONCLUIDO("Concluído");             // Morador lavou suas roupas

        private final String nome;


        Status(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }
    }

    public Agendamento() {
    }

    public Agendamento(UUID id, HorarioLiberado horarioLiberado, Usuario usuario, Status status) {
        this.id = id;
        this.horarioLiberado = horarioLiberado;
        this.usuario = usuario;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public HorarioLiberado getHorarioDisponivel() {
        return horarioLiberado;
    }

    public void setHorarioDisponivel(HorarioLiberado horario) {
        this.horarioLiberado = horario;
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
