package br.com.erico.lavanderia.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationFilter authenticationFilter;

    public SecurityConfig(CustomAuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/lavaceu/api/usuarios/moradores").hasAnyAuthority("ROLE_ADMIN", "ROLE_BOLSISTA")

                        .requestMatchers(HttpMethod.POST, "/lavaceu/api/lavadoras").hasAnyAuthority("ROLE_ADMIN", "ROLE_BOLSISTA")
                        .requestMatchers(HttpMethod.GET, "/lavaceu/api/lavadoras").hasAnyAuthority("ROLE_ADMIN", "ROLE_BOLSISTA")
                        .requestMatchers(HttpMethod.GET, "/lavaceu/api/lavadoras/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_BOLSISTA")
                        .requestMatchers(HttpMethod.PUT, "/lavaceu/api/lavadoras/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_BOLSISTA")
                        .requestMatchers(HttpMethod.PATCH, "/lavaceu/api/lavadoras/{id}/estado").hasAnyAuthority("ROLE_ADMIN", "ROLE_BOLSISTA")
                        .requestMatchers(HttpMethod.DELETE, "/lavaceu/api/lavadoras/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_BOLSISTA")
                        .anyRequest().authenticated())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
