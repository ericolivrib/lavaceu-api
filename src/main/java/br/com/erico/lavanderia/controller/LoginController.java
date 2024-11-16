package br.com.erico.lavanderia.controller;

import br.com.erico.lavanderia.dto.LoginDto;
import br.com.erico.lavanderia.dto.TokenDto;
import br.com.erico.lavanderia.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.matricula(),
                loginDto.senha()
        ));

        User user = (User) authentication.getPrincipal();
        TokenDto tokenDto = tokenService.getToken(user);

        return ResponseEntity.ok(tokenDto);
    }
}
