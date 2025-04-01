package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.EmprestimoResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record LivroComEmprestimoResponseDTO(List<LivroResponseDTO> Livros,
                                            List<EmprestimoResponseDTO> Emprestimos) {
}
