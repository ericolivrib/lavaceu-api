package br.com.erico.lavanderia.dto;

public interface UsuarioProjection {

    Long getId();

    String getNome();

    String getEmail();

    String getTelefone();

    String getMatricula();

    Integer getApartamento();

    String getAcesso();
}
