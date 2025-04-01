package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.AutorELivrosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroEAutorRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.AutorELivrosResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroEAutorResponseDTO;

import java.util.List;

public interface Autor_Livros {

    //Interface para criar métodos de associação entre autor e livro, e livro com autor, bidirecional


    //Associar autor a livro
    AutorELivrosResponseDTO associarAutorALivros(AutorELivrosRequestDTO autorELivrosRequestDTO);

    //Associar livro a autor
    List<LivroEAutorResponseDTO> associarLivrosAUmAutor(LivroEAutorRequestDTO livroEAutorRequestDTO);

    //Encontrar autor associado a seus livros
    List<AutorELivrosResponseDTO> encontrarAutorESeusLivrosAssociados(Integer id);

    //Encontrar autor associado a um livro


    //Deletar um livro associado ao autor

    //Deletar um autor associado a um livro

    //atualizar autor associado ao livro

    //atualizar livro associado ao autor
}
