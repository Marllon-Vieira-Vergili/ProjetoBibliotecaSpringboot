package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;

public record LivroComAutorResponse(Integer idLivro, String titulo, Integer anoLancamento, AutorComLivroResponse autor) {
}
