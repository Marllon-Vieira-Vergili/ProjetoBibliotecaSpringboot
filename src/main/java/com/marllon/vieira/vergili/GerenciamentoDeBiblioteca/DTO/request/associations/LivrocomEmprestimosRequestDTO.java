package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record LivrocomEmprestimosRequestDTO(@NotNull
                                            @Max(1) Integer LivroId,
                                            @NotNull
                                            @Max(1) Integer emprestimoId) {
}
