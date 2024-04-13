/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.config;

import br.com.lrz.betRaLa.models.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author lara
 */
@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    
    public String generateToken(Users user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("rala-api")
                    .withSubject(user.getEmail()).withExpiresAt(this.getExpirationDate())
                    .sign(algorithm);
        return token;            
        }catch(JWTCreationException e){
            throw new RuntimeException("Error While generating token", e);
        }
    }
    
    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
    
    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("rala-bet").build().verify(token).getSubject();
        }catch(JWTVerificationException e){
            return "";
        }
    }
}
