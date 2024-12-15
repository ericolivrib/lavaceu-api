package br.com.erico.lavanderia.service;

import br.com.erico.lavanderia.exception.HorarioExistenteException;
import br.com.erico.lavanderia.model.horario.HorarioAgendamento;
import br.com.erico.lavanderia.model.horario.HorarioAgendamentoRepository;
import br.com.erico.lavanderia.model.horario.PeriodoDia;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class HorarioAgendamentoService {

    private final HorarioAgendamentoRepository horarioAgendamentoRepository;

    public HorarioAgendamentoService(HorarioAgendamentoRepository horarioAgendamentoRepository) {
        this.horarioAgendamentoRepository = horarioAgendamentoRepository;
    }

    public void create(HorarioAgendamento horarioAgendamento) {
        if (horarioAgendamentoRepository.existsByHorario(horarioAgendamento.getHorario())) {
            throw new HorarioExistenteException();
        }

        PeriodoDia periodo = PeriodoDia.getInstance(horarioAgendamento.getHorario());
        horarioAgendamento.setPeriodo(periodo);

        horarioAgendamentoRepository.save(horarioAgendamento);
    }

    public HorarioAgendamento getById(long id) {
        return horarioAgendamentoRepository.findById(id).orElseThrow();
    }

    public List<HorarioAgendamento> getAll() {
        return horarioAgendamentoRepository.findAll();
    }

    public List<HorarioAgendamento> getAllByPeriodo(PeriodoDia periodo) {
        return horarioAgendamentoRepository.findAllByPeriodo(periodo);
    }

    public void atualizar(long id, HorarioAgendamento horarioAgendamento) {
        HorarioAgendamento ha = getById(id);
        LocalTime horario = horarioAgendamento.getHorario();

        ha.setHorario(horario);
        ha.setPeriodo(PeriodoDia.getInstance(horario));

        horarioAgendamentoRepository.save(ha);
    }

    public void deletar(long id) {
        HorarioAgendamento ha = getById(id);
        horarioAgendamentoRepository.delete(ha);
    }

}
