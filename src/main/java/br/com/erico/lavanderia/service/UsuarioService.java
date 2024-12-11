package br.com.erico.lavanderia.service;

import br.com.erico.lavanderia.dto.UsuarioProjection;
import br.com.erico.lavanderia.model.acesso.*;
import br.com.erico.lavanderia.exception.EmailExistenteException;
import br.com.erico.lavanderia.exception.MatriculaExistenteException;
import br.com.erico.lavanderia.model.usuario.Usuario;
import br.com.erico.lavanderia.model.usuario.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final AcessoRepository acessoRepository;
    private final AcessoUsuarioRepository acessoUsuarioRepository;

    public UsuarioService(
            PasswordEncoder passwordEncoder,
            UsuarioRepository usuarioRepository,
            AcessoRepository acessoRepository,
            AcessoUsuarioRepository acessoUsuarioRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.acessoRepository = acessoRepository;
        this.acessoUsuarioRepository = acessoUsuarioRepository;
    }

    public void adicionarMorador(Usuario morador) {
        if (usuarioRepository.existsByMatricula(morador.getMatricula())) {
            throw new MatriculaExistenteException();
        }

        if (usuarioRepository.existsByEmail(morador.getEmail())) {
            throw new EmailExistenteException();
        }

        morador.setSenha(passwordEncoder.encode(morador.getSenha()));

        usuarioRepository.save(morador);

        Acesso acesso = acessoRepository.getReferenceById(TipoAcesso.MORADOR.getAcessoId());

        AcessoUsuario acessoUsuario = new AcessoUsuario(
                new AcessoUsuarioId(acesso.getId(), morador.getId()),
                morador,
                acesso,
                null
        );

        acessoUsuarioRepository.save(acessoUsuario);
    }

    public void cadastrarBolsista(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        Acesso acesso = acessoRepository.getReferenceById(TipoAcesso.BOLSISTA.getAcessoId());

        AcessoUsuario acessoFromUsuario = new AcessoUsuario(
                new AcessoUsuarioId(acesso.getId(), usuario.getId()),
                usuario,
                acesso,
                usuario.getAcessos().getFirst().getUltimoAcesso()
        );

        acessoUsuarioRepository.save(acessoFromUsuario);
    }

    public List<UsuarioProjection> getUsuariosByAcesso(TipoAcesso tipoAcesso) {
        return usuarioRepository.findByAcessoId(tipoAcesso.getAcessoId());
    }

    public void atualizarUsuario(long usuarioId, Usuario usuario) {
        Usuario u = usuarioRepository.findById(usuarioId).orElseThrow();

        u.setNome(usuario.getNome());
        u.setEmail(usuario.getEmail());
        u.setMatricula(usuario.getMatricula());
        u.setApartamento(usuario.getApartamento());
        u.setTelefone(usuario.getTelefone());

        usuarioRepository.save(u);
    }
}
