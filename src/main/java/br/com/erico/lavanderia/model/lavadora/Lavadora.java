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
}
