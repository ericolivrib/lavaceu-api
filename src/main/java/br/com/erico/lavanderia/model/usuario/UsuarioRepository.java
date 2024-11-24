package br.com.erico.lavanderia.model.usuario;

import br.com.erico.lavanderia.dto.UsuarioProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByMatricula(String matricula);

    Optional<Usuario> findByNome(String nome);

    boolean existsByMatricula(String matricula);

    boolean existsByEmail(String email);

    @Query(value = """
            select u.usuario_id as id, u.nome, u.email, u.telefone, u.matricula, u.apartamento, a.nome as acesso
            from usuarios u, acessos_usuarios au, acessos a
            where au.usuario_id = u.usuario_id
            and au.acesso_id = a.acesso_id
            and a.acesso_id = :acessoId""", nativeQuery = true)
    List<UsuarioProjection> findByAcessoId(@Param("acessoId") long acessoId);
}
