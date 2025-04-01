package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.EmprestimoResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record LeitorComEmprestimoRequestDTO(
        @NotNull(message = "Não pode ser nulo a id do leitor")
        @Min(1) Integer LeitorId,

        @NotNull(message = "Não pode ser nulo a id do emprestimo")
        @Min(1)Integer EmprestimoId) {
}
