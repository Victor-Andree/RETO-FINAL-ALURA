package com.alura.foro.FORO_CHALLENGE.Security;

import com.alura.foro.FORO_CHALLENGE.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
                return JWT.create()
                        .withIssuer("API com.alura.foro")
                        .withSubject(usuario.getLogin())
                        .withExpiresAt(fechaExpiracion())
                        .sign(algorithm);

        } catch (JWTCreationException exception){
            throw  new RuntimeException(exception);
        }
    }
    public String getSubject(String tokenJWT) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algoritmo)
                    .withIssuer("API com.alura.foro")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(tokenJWT);
            return decodedJWT.getSubject();
        } catch (com.auth0.jwt.exceptions.TokenExpiredException ex) {
            throw new TokenExpiredException("The Token ha expirado o es inválido.");
        } catch (JWTVerificationException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}
