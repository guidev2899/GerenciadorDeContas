package com.example.contasapagar.contasapagar.services;

import com.example.contasapagar.contasapagar.entities.UserEntity;
import com.example.contasapagar.contasapagar.repositories.UserRepository;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class TokenService {



    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {

        this.jwtEncoder = jwtEncoder;
    }

    public String gerarToken(UserEntity user){
        var claims = JwtClaimsSet.builder()
                .issuer("contas a pagar")
                .subject(user.getEmail())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
