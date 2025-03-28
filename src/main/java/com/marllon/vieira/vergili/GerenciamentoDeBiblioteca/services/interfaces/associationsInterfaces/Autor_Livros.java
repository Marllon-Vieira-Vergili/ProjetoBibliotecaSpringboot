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
}
