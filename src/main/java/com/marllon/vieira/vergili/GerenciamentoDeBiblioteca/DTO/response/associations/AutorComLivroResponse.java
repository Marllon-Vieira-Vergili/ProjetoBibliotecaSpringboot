package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;


public record AutorComLivroResponse(Integer idAutor, String nomeAutor, String cidadeAutor, LivroComAutorResponse livro) {
}


