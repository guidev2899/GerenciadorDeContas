package com.example.contasapagar.contasapagar.controllers;


import com.example.contasapagar.contasapagar.Dtos.UserRegisterDto;
import com.example.contasapagar.contasapagar.entities.UserEntity;
import com.example.contasapagar.contasapagar.repositories.UserRepository;
import com.example.contasapagar.contasapagar.services.TokenService;
import com.example.contasapagar.contasapagar.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    private final UserRepository repository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private TokenService tokenService;


    public UserController(UserService userService, UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenService tokenService) {
        this.userService = userService;
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDto dto) {
        Optional<UserEntity> user = repository.findByemail(dto.email());
        if(user.isPresent()){
            throw new RuntimeException("Usuario já cadastrado");
        }
        UserEntity newUser = new UserEntity();
        newUser.setNome(dto.nome());
        newUser.setEmail(dto.email());
        newUser.setPassword(bCryptPasswordEncoder.encode(dto.password()));
        var token = tokenService.gerarToken(newUser);
        return ResponseEntity.ok(token);


    }
}
