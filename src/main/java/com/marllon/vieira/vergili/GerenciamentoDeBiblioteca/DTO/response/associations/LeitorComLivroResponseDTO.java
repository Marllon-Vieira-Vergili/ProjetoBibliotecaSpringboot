package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponseDTO;


import java.util.List;

public record LeitorComLivroResponseDTO(Integer id, String nome, String sobrenome, String email,
                                        Integer idade, List<LivroResponseDTO> listaLivrosRelacionadosAoLeitor) {
}
