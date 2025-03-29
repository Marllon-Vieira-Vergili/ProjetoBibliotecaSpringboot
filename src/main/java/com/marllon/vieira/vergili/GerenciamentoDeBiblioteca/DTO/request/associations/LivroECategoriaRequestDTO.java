package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;

import java.util.List;

public record LivroECategoriaRequestDTO(Iterable<Integer> livrosId, Integer categoriaId) {
}
