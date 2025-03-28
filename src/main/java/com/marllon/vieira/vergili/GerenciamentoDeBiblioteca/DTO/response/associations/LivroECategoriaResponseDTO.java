package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.CategoriaResponseDTO;

import java.util.List;

public record LivroECategoriaResponseDTO(Integer id, String nome, Integer anoLancamento,
                                         List<CategoriaResponseDTO> listaLivrosComCategoria) {
}
