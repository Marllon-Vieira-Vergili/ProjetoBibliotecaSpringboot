package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record LeitorComLivroRequestDTO(
        @NotNull(message = "Não pode ser nulo a id do leitor")
        @Max(value = 1) Integer leitorId,
        @NotNull(message = "Não pode ser nulo a id do livro")
        @Max(value = 1) Integer livroId) {
}
