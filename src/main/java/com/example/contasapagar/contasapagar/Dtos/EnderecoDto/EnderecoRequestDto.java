package com.example.contasapagar.contasapagar.Dtos.EnderecoDto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequestDto(
        @NotBlank(message = "Estado precisa ser informado")
        String estado,

        @NotBlank(message = "Cidade precisa ser informada")
        String cidade,

        @NotBlank(message = "Rua precisa ser informada")
        String rua,

        @NotBlank(message = "Número precisa ser informado")
        Integer numero,

        @NotBlank(message = "Cep precisa ser informado")
        String cep
) {
}
