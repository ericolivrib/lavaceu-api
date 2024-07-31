package com.erico.ceu.lavaceu.domain.horario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface HorarioLiberadoRepository extends JpaRepository<HorarioLiberado, UUID> {

    List<HorarioLiberado> findByHorarioId(UUID horarioId);

    boolean existsByHorarioIdAndData(UUID horarioId, LocalDate data);

    boolean existsByHorarioId(UUID horarioId);

}
