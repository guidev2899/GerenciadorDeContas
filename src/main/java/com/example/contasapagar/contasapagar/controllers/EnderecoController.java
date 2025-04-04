package com.example.contasapagar.contasapagar.controllers;

import com.example.contasapagar.contasapagar.Dtos.EnderecoDto.EnderecoRequestDto;
import com.example.contasapagar.contasapagar.entities.EnderecoEntity;
import com.example.contasapagar.contasapagar.entities.UserEntity;
import com.example.contasapagar.contasapagar.repositories.UserRepository;
import com.example.contasapagar.contasapagar.services.EnderecoService;

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
public class EnderecoController {

    private final EnderecoService enderecoService;

    private final UserService userService;

    private final TokenService tokenService;

    public EnderecoController(
            EnderecoService enderecoService, UserService userService,
            TokenService tokenService) {
        this.enderecoService = enderecoService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/register/endereco")
    public ResponseEntity<String> registerEnd(@RequestHeader("Authorization") String token,
                                              @Valid @RequestBody EnderecoRequestDto dto){
        EnderecoEntity endereco = UtilsService.fromEnderecoEntity(dto);
        String accessToken = token.replace("Bearer ", "");
        String subject = tokenService.getSubject(accessToken);
        UserEntity user = userService.getUserByEmail(subject).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );
        enderecoService.createEndereco(endereco);
        user.setEndereco(endereco);
        userService.createUser(user);
        return ResponseEntity.ok("Endereço cadastrado com sucesso");
    }
}
