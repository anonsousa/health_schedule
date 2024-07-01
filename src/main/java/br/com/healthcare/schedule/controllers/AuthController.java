package br.com.healthcare.schedule.controllers;

import br.com.healthcare.schedule.domain.dtos.AuthUserLoginDto;
import br.com.healthcare.schedule.domain.dtos.TokenDto;
import br.com.healthcare.schedule.domain.entities.UserEntity;
import br.com.healthcare.schedule.domain.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthUserLoginDto loginDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.password()
                );
        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        TokenDto token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
