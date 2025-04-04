package com.example.contasapagar.contasapagar.controllers;

import com.example.contasapagar.contasapagar.Dtos.ContaRequestDto;
import com.example.contasapagar.contasapagar.entities.ContasEntity;
import com.example.contasapagar.contasapagar.entities.UserEntity;
import com.example.contasapagar.contasapagar.services.ContasService;
import com.example.contasapagar.contasapagar.services.TokenService;
import com.example.contasapagar.contasapagar.services.UserService;
import com.example.contasapagar.contasapagar.utils.UtilsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class ContasController {

    private final ContasService contasService;

    private final UserService userService;

    private final TokenService tokenService;


    public ContasController(ContasService contasService,
                            UserService userService, TokenService tokenService) {
        this.contasService = contasService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/register/conta")
    public ResponseEntity<String> registrarConta(@Valid @RequestBody ContaRequestDto contaRequestDto,
                                                 @RequestHeader("Authorization") String token){
        String accesstoken = token.replace("Bearer ", "");
        String subject = tokenService.getSubject(accesstoken);
        UserEntity user = userService.getUserByEmail(subject)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        ContasEntity contaEntity = UtilsService.fromContaEntity(contaRequestDto);
        contaEntity.setUserEntity(user);
        contasService.createConta(contaEntity);
        return ResponseEntity.ok("Conta criada com sucesso");
    }
}
