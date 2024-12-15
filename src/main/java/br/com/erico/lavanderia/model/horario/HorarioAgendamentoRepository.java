package br.com.erico.lavanderia.model.horario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface HorarioAgendamentoRepository extends JpaRepository<HorarioAgendamento, Long> {

    boolean existsByHorario(LocalTime horario);

    List<HorarioAgendamento> findAllByPeriodo(PeriodoDia periodo);
}
