package com.erico.ceu.lavaceu.domain.horario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HorarioDisponivelRepository extends JpaRepository<HorarioDisponivel, UUID> {

    List<HorarioDisponivel> findByHorarioId(UUID horarioId);

}
