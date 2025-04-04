package com.example.contasapagar.contasapagar.controllers;


import com.example.contasapagar.contasapagar.Dtos.UserDto.UserRegisterDto;
import com.example.contasapagar.contasapagar.entities.UserEntity;
import com.example.contasapagar.contasapagar.repositories.UserRepository;
import com.example.contasapagar.contasapagar.services.TokenService;
import com.example.contasapagar.contasapagar.services.UserService;
import com.example.contasapagar.contasapagar.utils.UtilsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    private final UserRepository repository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final TokenService tokenService;


    public UserController(UserService userService, UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenService tokenService) {
        this.userService = userService;
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterDto dto) {
        Optional<UserEntity> user = repository.findByemail(dto.email());
        if(user.isPresent()){
            throw new RuntimeException("Usuario já cadastrado");
        }
        UserEntity newUser = UtilsService.fromUserEntity(dto, bCryptPasswordEncoder);
        var token = tokenService.gerarToken(newUser);
        userService.createUser(newUser);
        return ResponseEntity.ok(token);
    }



}
