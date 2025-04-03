package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.LivroRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroRequestComAutor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroComAutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;

import java.util.List;

public interface LivroAndAssociationService {

    //Criar
    LivroComAutorResponse criarLivroComAutor(LivroRequestComAutor livroRequestComAutor); //usado na Api Rest

    //Ler
    LivroComAutorResponse lerLivroComAutor(Integer id);
    List<LivroComAutorResponse> lerTodosLivros();
    //aqui ja ler um autor e seus livros relacionados

    //Atualizar

    //atualizar somente os autores, sem seus livros associados, e mostrar os livros associados junto

    //Deletar

    //deletar o autor, e seus livros associados

}
