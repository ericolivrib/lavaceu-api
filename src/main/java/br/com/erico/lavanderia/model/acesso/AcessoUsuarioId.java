package br.com.erico.lavanderia.model.acesso;

import jakarta.persistence.Embeddable;

@Embeddable
public class AcessoUsuarioId {

    private Long acessoId;
    private Long usuarioId;

    public Long getAcessoId() {
        return acessoId;
    }

    public void setAcessoId(Long acessoId) {
        this.acessoId = acessoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
