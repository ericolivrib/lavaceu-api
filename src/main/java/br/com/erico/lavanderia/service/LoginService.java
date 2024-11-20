package br.com.erico.lavanderia.service;

import br.com.erico.lavanderia.model.usuario.Usuario;
import br.com.erico.lavanderia.model.usuario.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LoginService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public LoginService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByMatricula(matricula)
                .orElseThrow(() -> new UsernameNotFoundException("Matrícula ou senha incorretas"));

        String authorities = usuario.getAcessos()
                .stream()
                .map(au -> "ROLE_" + au.getAcesso().getNome())
                .collect(Collectors.joining(" "));

        return User.withUsername(matricula)
                .password(usuario.getSenha())
                .authorities(authorities)
                .build();
    }
}
