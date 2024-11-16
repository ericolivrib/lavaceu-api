package br.com.erico.lavanderia.service;

import br.com.erico.lavanderia.dto.TokenDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public TokenDto getToken(User user) {
        String jwt = gerarHashToken(user);
        return new TokenDto(jwt);
    }

    public String gerarHashToken(User user) {
        String issuerClaim = "lavaceuapi";

        String scopesClaim = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        long expirationTimeSeconds = 3600;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(issuerClaim)
                .subject(user.getUsername())
                .expiresAt(Instant.now().plusSeconds(expirationTimeSeconds))
                .claim("scopes", scopesClaim)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
