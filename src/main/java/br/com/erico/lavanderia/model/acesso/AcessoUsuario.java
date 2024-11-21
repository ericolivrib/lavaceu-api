package br.com.erico.lavanderia.model.acesso;

import br.com.erico.lavanderia.model.usuario.Usuario;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "acessos_usuarios")
public class AcessoUsuario {

    @EmbeddedId
    private AcessoUsuarioId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("acessoId")
    @JoinColumn(name = "acesso_id")
    private Acesso acesso;

    @UpdateTimestamp
    private Instant ultimoAcesso;

    public AcessoUsuario(
            AcessoUsuarioId id,
            Usuario usuario,
            Acesso acesso,
            Instant ultimoAcesso
    ) {
        this.id = id;
        this.usuario = usuario;
        this.acesso = acesso;
        this.ultimoAcesso = ultimoAcesso;
    }

    public AcessoUsuario() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AcessoUsuario that = (AcessoUsuario) o;
        return Objects.equals(id, that.id)
                && Objects.equals(usuario, that.usuario)
                && Objects.equals(acesso, that.acesso)
                && Objects.equals(ultimoAcesso, that.ultimoAcesso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, acesso, ultimoAcesso);
    }

    public void setId(AcessoUsuarioId id) {
        this.id = id;
    }

    public AcessoUsuarioId getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }

    public Instant getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Instant ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }
}
