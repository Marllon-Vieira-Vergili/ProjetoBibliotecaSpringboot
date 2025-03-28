package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.EmprestimoResponseDTO;

import java.util.List;

public record LeitorComEmprestimoResponseDTO(Integer id, String nome, String sobrenome, String email,
                                             Integer idade, List<EmprestimoResponseDTO> listaEmprestimosRelacionadosAoLeitor) {
}
