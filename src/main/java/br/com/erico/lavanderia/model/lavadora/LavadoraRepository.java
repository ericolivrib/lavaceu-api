package br.com.erico.lavanderia.model.lavadora;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LavadoraRepository extends JpaRepository<Lavadora, Long> {
}
