package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.CategoriaELivrosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroECategoriaRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.CategoriaELivrosResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroECategoriaResponseDTO;

public interface Categoria_Livros {

    //Interface para criar métodos de associação entre categoria e livros, e livros com categoria, bidirecional

    //Associar Categoria a livro
    CategoriaELivrosResponseDTO associarCategoriaALivro(CategoriaELivrosRequestDTO categoriaELivrosRequestDTO);

    //Associar livro a Categoria
    LivroECategoriaResponseDTO associarLivroACategoria(LivroECategoriaRequestDTO livroECategoriaRequestDTO);
}
