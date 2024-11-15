package br.com.erico.lavanderia.model.acesso;

public enum TipoAcesso {

    ADMIN(1),
    MORADOR(2),
    BOLSISTA(3);

    private final long acessoId;

    TipoAcesso(long acessoId) {
        this.acessoId = acessoId;
    }

    public long getAcessoId() {
        return acessoId;
    }
}
