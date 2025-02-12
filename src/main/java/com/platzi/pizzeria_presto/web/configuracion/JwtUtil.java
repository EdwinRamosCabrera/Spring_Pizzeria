package com.platzi.pizzeria_presto.web.configuracion;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "pizz3ria_pr3sto"; // nuestra palabra clave o llave de inscripción
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY); // usamos el algoritmo HMAC256 y le pasamos nuestra clave
    public String create(String username){
        return JWT.create() // el JWT es de com.auth0.jwt
                .withSubject(username)  // el asunto siempre será el usuario en cuestión
                .withIssuer("pizzeria-presto") // quien crea este JWT
                .withIssuedAt(new Date()) // fecha en la que se creó este token, que es la actual
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15))) // cuando expira, dentro de 15 dias
                .sign(ALGORITHM); // firmar nuestro Token, aquí se recibe un algoritmo, retorna un string
    }

    public boolean isValid(String jwt){
        try{
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
            return true;
        }catch (JWTVerificationException e){
            return false;
        }
    }

    public String getUsername(String jwt){
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject(); // elegimos el Subject porque cuando creamos nuestro jwt dijimos que su Subject iba a ser el usuario
    }
}
