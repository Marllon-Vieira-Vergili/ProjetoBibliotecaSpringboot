package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.EmprestimoResponseDTO;

import java.util.List;

public record EmprestimoELeitoresResponseDTO(Integer id, String nomeLeitor, String sobrenomeLeitor,
                                             Integer idadeLeitor, String emailLeitor,
                                             List<EmprestimoResponseDTO> emprestimos) {
}
