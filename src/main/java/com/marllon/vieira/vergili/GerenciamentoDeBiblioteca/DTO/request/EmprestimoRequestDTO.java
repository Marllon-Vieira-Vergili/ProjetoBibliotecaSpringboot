package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request;
import java.time.LocalDate;

public record EmprestimoRequestDTO(LocalDate dataEmprestimo, LocalDate dataDevolucao) {
}

