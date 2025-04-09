package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.CategoriaResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.LivroResponse;

import java.util.List;

//Resposta das entidades do método de categoria para livro
public record CategoriaLivroResponse(CategoriaResponse categoriaAssociada, List<LivroResponse> livrosAssociados){
}
