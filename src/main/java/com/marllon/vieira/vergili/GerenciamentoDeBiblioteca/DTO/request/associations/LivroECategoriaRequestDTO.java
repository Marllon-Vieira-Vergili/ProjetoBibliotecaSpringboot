package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record LivroECategoriaRequestDTO(@Max(value = 1)
                                        @NotNull(message = "Deve conter pelo menos uma categoria!")
                                        Integer categoriaId,
                                        @Min(value = 1, message = "Deve conter pelo menos 1 id de um livro!")
                                                @NotNull
                                        List<Integer> livroId) {
}
