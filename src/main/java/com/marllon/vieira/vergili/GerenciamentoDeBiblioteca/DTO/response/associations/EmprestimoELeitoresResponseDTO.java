package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LeitorResponseDTO;



import java.time.LocalDate;
import java.util.List;

public record EmprestimoELeitoresResponseDTO(Integer id, LocalDate dataEmprestimo, LocalDate dataDevolucao,
                                             boolean estaEmprestado, List<LeitorResponseDTO> listaLeitoresComEmprestimos) {
}
