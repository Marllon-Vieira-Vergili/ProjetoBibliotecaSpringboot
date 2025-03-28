package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;


public record LivroEAutorRequestDTO(@NotNull(message = "Deve possuir pelo menos uma ID!")
                                    @Size(min = 1) Set<Integer> livrosIds,
                                    @NotNull(message = "Deve possuir pelo menos um autor relacionado(ID)!")
                                    @Size(max = 1) Integer IdAutorRelacionado) {
}
