package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.AutorRequest;

public record LivroRequestComAutor(String titulo, Integer anoLancamento, AutorRequest Autor) {
}
