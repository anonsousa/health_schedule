package br.com.healthcare.schedule.domain.service;

import br.com.healthcare.schedule.domain.dtos.TokenDto;
import br.com.healthcare.schedule.domain.entities.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${my.secret.key}")
    private String secret;

    public TokenDto generateToken(UserEntity user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT
                    .create()
                    .withIssuer("schedule")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpiresDate())
                    .sign(algorithm);
            return new TokenDto(user.getEmail(), token);
        } catch (JWTCreationException e){
            throw new RuntimeException("Nao foi possivel gerar o token");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT
                    .require(algorithm)
                    .withIssuer("schedule")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e){
            return "";
        }
    }


    public Instant generateExpiresDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
