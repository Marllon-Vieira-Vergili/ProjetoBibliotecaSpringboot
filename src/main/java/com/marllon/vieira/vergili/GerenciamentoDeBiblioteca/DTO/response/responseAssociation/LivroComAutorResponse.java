package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.AutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.LivroResponse;

import java.util.List;

//Funcionou para adicionar livro ao autor
public record LivroComAutorResponse(List<LivroResponse> Livros, AutorResponse Autor) {
}
