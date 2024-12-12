package br.com.erico.lavanderia.service;

import br.com.erico.lavanderia.dto.TokenDto;
import com.nimbusds.jwt.JWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public TokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
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

    public String getSubject(String token) throws JwtException {
        return jwtDecoder.decode(token).getSubject();
    }

}
