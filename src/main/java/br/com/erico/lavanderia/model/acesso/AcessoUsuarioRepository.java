package br.com.erico.lavanderia.model.acesso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcessoUsuarioRepository extends JpaRepository<AcessoUsuario, AcessoUsuarioId> {
}
