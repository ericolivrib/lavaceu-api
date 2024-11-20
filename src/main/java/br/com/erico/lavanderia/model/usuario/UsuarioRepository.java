package br.com.erico.lavanderia.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByMatricula(String matricula);

    Optional<Usuario> findByNome(String nome);

    boolean existsByMatricula(String matricula);

    boolean existsByEmail(String email);
}
