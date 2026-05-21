package br.com.fiap.biooffset.config.security;

import br.com.fiap.biooffset.model.Empresa;
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

    @Value("${palavra.secreta}")
    private String palavraSecreta;

    public String gerarToken(Empresa empresa) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);

            String token = JWT.create()
                    .withIssuer("biooffset")
                    .withSubject(empresa.getCnpj())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Não foi possível gerar token.");
        }
    }

    public String validarToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);

            return JWT.require(algorithm)
                    .withIssuer("biooffset")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e){
            return "";
        }
    }

    private Instant gerarDataExpiracao(){
        return LocalDateTime
                .now()
                .plusDays(1)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
