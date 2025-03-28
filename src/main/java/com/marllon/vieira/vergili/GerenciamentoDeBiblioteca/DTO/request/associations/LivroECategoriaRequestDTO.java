package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;

import java.util.List;

public record LivroECategoriaRequestDTO(List<Categoria> listaLivrosComCategoria) {
}
