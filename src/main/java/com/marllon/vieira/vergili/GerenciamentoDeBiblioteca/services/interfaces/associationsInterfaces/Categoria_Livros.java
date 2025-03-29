package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.CategoriaELivrosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroECategoriaRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.CategoriaELivrosResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroECategoriaResponseDTO;

import java.util.List;

public interface Categoria_Livros {

    //Interface para criar métodos de associação entre categoria e livros, e livros com categoria, bidirecional

    //Associar Categoria a livro
    CategoriaELivrosResponseDTO associarCategoriaALivro(CategoriaELivrosRequestDTO categoriaELivrosRequestDTO);

    //Associar livro a Categoria
    List<LivroECategoriaResponseDTO> associarLivroACategoria(LivroECategoriaRequestDTO livroECategoriaRequestDTO);

    //Encontrar uma categoria e seus livros associados

    //Encontrar livros associados a uma categoria

    //Deletar um livro associado a uma categoria

    //Deletar uma categoria associado a um livro

    //atualizar um livro associado a uma categoria

    //atualizar uma categoria associado aos livros contidos nela
}
