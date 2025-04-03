package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.AutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponse;

import java.util.List;

//Funcionou para adicionar livro ao autor
public record LivroComAutorResponse(List<LivroResponse> Livros, AutorResponse Autor) {
}
