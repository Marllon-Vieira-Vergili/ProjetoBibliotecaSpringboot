package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation.LivroRequestComAutor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LivroAssociationsResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LivroComAutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;

import java.util.List;

public interface LivroAndAssociationService {

    //Criar
    LivroComAutorResponse criarLivroComAutor(LivroRequestComAutor livroRequestComAutor); //usado na Api Rest

    //Ler
    LivroAssociationsResponse encontarUmLivro(Integer id);
    List<LivroAssociationsResponse> encontrarTodosLivros();

    //Atualizar

    //atualizar somente os autores, sem seus livros associados, e mostrar os livros associados junto

    //Deletar

    //deletar o autor, e seus livros associados

}
