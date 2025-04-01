package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponseDTO;


import java.time.LocalDate;
import java.util.List;

public record EmprestimoELivrosResponseDTO(Integer id, LocalDate dataEmprestimo, LocalDate dataDevolucao,
                                           boolean estaEmprestado, List<LivroResponseDTO> LivroEmprestados) {
}
