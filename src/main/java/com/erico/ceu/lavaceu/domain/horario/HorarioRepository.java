package com.erico.ceu.lavaceu.domain.horario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, UUID> {

    List<Horario> findByDiaSemana(DiaSemana diaSemana);

    boolean existsByDiaSemanaAndHora(DiaSemana diaSemana, LocalTime hora);

}
