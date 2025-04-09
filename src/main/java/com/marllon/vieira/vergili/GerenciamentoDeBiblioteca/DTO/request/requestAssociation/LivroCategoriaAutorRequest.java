package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.CategoriaRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LivroRequest;

import java.util.List;

//AdicinarLivro e associar autor e categoria, ou vice versa.. adicionar autor e livro associado, com categoria
//Para os métodos livro, categoria, e a autor
public record LivroCategoriaAutorRequest(LivroRequest Livro, AutorRequest Autor, CategoriaRequest Categoria) {
}
