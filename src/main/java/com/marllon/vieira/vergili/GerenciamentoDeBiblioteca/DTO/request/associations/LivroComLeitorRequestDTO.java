package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record LivroComLeitorRequestDTO(
        @NotNull(message = "Id do livro não pode ser nulo!")
        @Max(value = 1) Integer livroId,

        @NotNull(message = "id do leitor não pode ser nulo!")
        @Max(value = 1) Integer leitorId) {
}
