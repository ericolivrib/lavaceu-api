package br.com.erico.lavanderia.infra.security;

import br.com.erico.lavanderia.service.LoginService;
import br.com.erico.lavanderia.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationFilter.class);

    private final TokenService tokenService;
    private final LoginService loginService;

    public CustomAuthenticationFilter(TokenService tokenService, LoginService loginService) {
        this.tokenService = tokenService;
        this.loginService = loginService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("{} {}", request.getMethod(), request.getRequestURI());

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        try {
            if (token != null) {
                token = token.replace("Bearer", "").trim();

                String validatedToken = tokenService.validarToken(token);

                if (validatedToken != null) {
                    String subject = tokenService.getSubject(validatedToken);
                    UserDetails userDetails = loginService.loadUserByUsername(subject);
                    var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (JwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
        }

        filterChain.doFilter(request, response);
    }
}
