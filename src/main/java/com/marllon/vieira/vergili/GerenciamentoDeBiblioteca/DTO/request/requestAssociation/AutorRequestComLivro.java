package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LivroRequest;

//Funcionou para adicionar autor e associar seus livros
public record AutorRequestComLivro(String nome, String email, String telefone, String cidade, LivroRequest Livro) {
}
