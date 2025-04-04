package com.example.contasapagar.contasapagar.controllers;

import com.example.contasapagar.contasapagar.Dtos.UserDto.UserLoginDtoRequest;
import com.example.contasapagar.contasapagar.entities.UserEntity;
import com.example.contasapagar.contasapagar.services.TokenService;
import com.example.contasapagar.contasapagar.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    private final TokenService tokenService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserService userService;


    public AuthController(TokenService tokenService, BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.tokenService = tokenService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @PostMapping("/token")
    public ResponseEntity<String> gerarNovoToken(@RequestBody UserLoginDtoRequest userLoginDtoRequest) {
        UserEntity user = userService.getUserByEmail(userLoginDtoRequest.email())
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado")); //Tratar Exceção
        if(!bCryptPasswordEncoder.matches(userLoginDtoRequest.password(), user.getPassword())){
            throw new BadCredentialsException("Usuário inválido");
        }
        String token = tokenService.gerarToken(user);
        return ResponseEntity.ok(token);
    }
}
