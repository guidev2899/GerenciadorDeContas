package com.example.contasapagar.contasapagar.Dtos.UserDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterDto (
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "Senha Obrigatória")
        String password,

        @NotBlank(message = "Nome obrigatório")
        String nome
){}
