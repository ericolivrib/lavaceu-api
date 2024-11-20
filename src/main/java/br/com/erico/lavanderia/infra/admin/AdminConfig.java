package br.com.erico.lavanderia.infra.admin;

import br.com.erico.lavanderia.model.acesso.*;
import br.com.erico.lavanderia.model.usuario.Usuario;
import br.com.erico.lavanderia.model.usuario.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Configuration
public class AdminConfig implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminConfig.class);

    @Value("${api.admin.matricula}")
    private String matriculaAdmin;
    @Value("${api.admin.senha}")
    private String senhaAdmin;

    private final AcessoUsuarioRepository acessoUsuarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final AcessoRepository acessoRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminConfig(
            AcessoUsuarioRepository acessoUsuarioRepository,
            UsuarioRepository usuarioRepository,
            AcessoRepository acessoRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.acessoUsuarioRepository = acessoUsuarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.acessoRepository = acessoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Acesso acessoAdmin = acessoRepository.getReferenceByNome(TipoAcesso.ADMIN.name());
        Optional<Usuario> adminExistente = usuarioRepository.findByMatricula(matriculaAdmin);

        adminExistente.ifPresentOrElse((u) -> log.info("Usuário administrador configurado"),
                () -> {
                    log.info("Configurando usuário administrador");

                    Usuario novoAdmin = new Usuario(
                            null,
                            "Administrador",
                            passwordEncoder.encode(senhaAdmin),
                            "00 00000-0000",
                            matriculaAdmin,
                            1000,
                            null
                    );

                    usuarioRepository.save(novoAdmin);

                    AcessoUsuario acessoUsuario = new AcessoUsuario(
                            new AcessoUsuarioId(acessoAdmin.getId(), novoAdmin.getId()),
                            novoAdmin,
                            acessoAdmin,
                            null
                    );
                    acessoUsuarioRepository.save(acessoUsuario);
                }
        );

    }
}
