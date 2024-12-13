package br.com.erico.lavanderia.model.lavadora;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "lavadoras")
public class Lavadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lavadora_id")
    private Long id;

    @NotBlank(message = "Informe o nome da marca da lavadora")
    @Column(name = "marca")
    private String marca;

    @NotNull(message = "Informe o tempo de lavagem")
    @Column(name = "tempo_lavagem")
    private Integer tempoLavagem;

    @Pattern(regexp = "DISPONIVEL|OCUPADA|MANUTENCAO|QUEBRADA", message = "Informe um dos valores: DISPONIVEL, OCUPADA, MANUTENCAO ou QUEBRADA")
    @Column(name = "estado")
    private String estado;

    public Lavadora() {
    }

    public Lavadora(Long id, String marca, Integer tempoLavagem, String estado) {
        this.id = id;
        this.marca = marca;
        this.tempoLavagem = tempoLavagem;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getTempoLavagem() {
        return tempoLavagem;
    }

    public void setTempoLavagem(Integer tempoLavagem) {
        this.tempoLavagem = tempoLavagem;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
