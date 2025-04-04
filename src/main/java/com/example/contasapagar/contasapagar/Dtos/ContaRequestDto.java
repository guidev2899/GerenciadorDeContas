package com.example.contasapagar.contasapagar.Dtos;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaRequestDto(
    @NotBlank(message = "Descrição não pode ser vazio")
    String descricao,


    BigDecimal valor,


    LocalDate data
) {
}
