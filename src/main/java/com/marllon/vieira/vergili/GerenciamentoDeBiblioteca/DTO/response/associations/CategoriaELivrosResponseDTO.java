package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.CategoriaResponseDTO;


import java.util.List;

public record CategoriaELivrosResponseDTO(Integer id, String nomeCategoria, List<CategoriaResponseDTO> listaLivrosRelacionados) {
}
