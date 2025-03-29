package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;

import java.util.List;

public record LivroECategoriaResponseDTO(List<Livro> listaLivros,Integer categoriaId, String nomeCategoria) {
}
