package br.com.erico.lavanderia.service;

import br.com.erico.lavanderia.model.acesso.*;
import br.com.erico.lavanderia.exception.EmailExistenteException;
import br.com.erico.lavanderia.exception.MatriculaExistenteException;
import br.com.erico.lavanderia.model.usuario.Usuario;
import br.com.erico.lavanderia.model.usuario.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UsuarioService {

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

        morador.setNome(morador.getNome().toUpperCase());
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
}
