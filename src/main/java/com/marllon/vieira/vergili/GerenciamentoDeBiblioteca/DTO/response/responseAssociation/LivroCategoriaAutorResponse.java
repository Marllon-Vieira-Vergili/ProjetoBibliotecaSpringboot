package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.AutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.CategoriaResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.LivroResponse;

import java.util.List;


//Resposta do relacionamento um autor para Vàrios livros, ou vice versa, de livros para autor, e categorias
//para os métodos livro, e autor, e categoria
public record LivroCategoriaAutorResponse(List<LivroResponse> Livros, List<CategoriaResponse> Categorias, AutorResponse Autor) {
}

