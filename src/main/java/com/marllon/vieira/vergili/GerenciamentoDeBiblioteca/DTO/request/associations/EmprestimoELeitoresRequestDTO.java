package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public record EmprestimoELeitoresRequestDTO(
        @NotNull(message = "id do emprestimo não pode ser nulo")
        @Max(1) Integer emprestimoId,
        @NotNull(message = "id do emprestimo não pode ser nulo")
        @Max(1) Integer leitorId) {
}
