package br.com.erico.lavanderia.service;

import br.com.erico.lavanderia.model.lavadora.Lavadora;
import br.com.erico.lavanderia.model.lavadora.LavadoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LavadoraService {

    private final LavadoraRepository lavadoraRepository;

    public LavadoraService(LavadoraRepository lavadoraRepository) {
        this.lavadoraRepository = lavadoraRepository;
    }

    public void cadastrarLavadora(Lavadora lavadora) {
        lavadoraRepository.save(lavadora);
    }

    public Lavadora getLavadoraById(long id) {
        return lavadoraRepository.findById(id).orElseThrow();
    }

    public List<Lavadora> getAllLLavadoras() {
        return lavadoraRepository.findAll();
    }

    public void atualizarLavadora(long id, Lavadora lavadora) {
        Lavadora l = getLavadoraById(id);

        l.setMarca(lavadora.getMarca());
        l.setNumero(lavadora.getNumero());
        l.setTempoLavagem(lavadora.getTempoLavagem());

        lavadoraRepository.save(l);
    }

    public void atualizarEstadoLavadora(long id, String estado) {
        Lavadora lavadora = getLavadoraById(id);
        lavadora.setEstado(estado);
        lavadoraRepository.save(lavadora);
    }

    public void deletarLavadora(long id) {
        Lavadora lavadora = getLavadoraById(id);
        lavadoraRepository.delete(lavadora);
    }
}
