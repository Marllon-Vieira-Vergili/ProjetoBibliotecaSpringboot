package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.EmprestimoResponseDTO;

import java.util.List;

public record LivrocomEmprestimosResponseDTO(Integer id, String nome, Integer anoLancamento,
                                             List<EmprestimoResponseDTO> listaEmprestimosRelacionados) {
}
