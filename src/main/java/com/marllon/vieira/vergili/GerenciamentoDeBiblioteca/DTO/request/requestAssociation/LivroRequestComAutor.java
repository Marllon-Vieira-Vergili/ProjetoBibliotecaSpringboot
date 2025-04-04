package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.AutorRequest;

public record LivroRequestComAutor(String titulo, Integer anoLancamento, AutorRequest Autor) {
}
