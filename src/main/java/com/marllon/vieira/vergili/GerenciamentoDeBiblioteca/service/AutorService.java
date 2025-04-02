package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.LivroRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.AutorComLivroResponse;


public interface AutorService {

    //Criar
    void criarAutor(AutorRequest autorRequest);//associação bidirecional

    AutorComLivroResponse criarAutorComLivro(AutorRequest autorRequest, LivroRequest livroRequest); //usado na Api Rest

    //Ler

    //aqui ja ler um autor e seus livros relacionados

    //Atualizar

    //atualizar somente os autores, sem seus livros associados, e mostrar os livros associados junto

    //Deletar

    //deletar o autor, e seus livros associados

}
