package br.com.erico.lavanderia.model.lavadora;

import jakarta.persistence.*;

@Entity
@Table(name = "lavadoras")
public class Lavadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lavadora_id")
    private Long id;

    @Column(name = "marca")
    private String marca;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "tempo_lavagem")
    private Integer tempoLavagem;

    @Column(name = "estado")
    private String estado;

    public Lavadora() {
    }

    public Lavadora(Long id, String marca, Integer numero, Integer tempoLavagem, String estado) {
        this.id = id;
        this.marca = marca;
        this.numero = numero;
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
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
