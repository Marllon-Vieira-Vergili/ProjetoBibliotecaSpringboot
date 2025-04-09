package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation.LivroCategoriaAutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.AutorRequest;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LivroCategoriaAutorResponse;

import java.util.List;


public interface AutorAssociationService {

    //Criar autor e os livros dele associado
    LivroCategoriaAutorResponse criarAutorComLivro(LivroCategoriaAutorRequest livroCategoriaAutorRequest); //usado na Api Rest

    //Ler autor e os livros dele associado
    LivroCategoriaAutorResponse lerAutorPorId(Integer id);
    List<LivroCategoriaAutorResponse> lerTodosAutores();

    //Atualizar autor e mostrar os livros dele associado
    LivroCategoriaAutorResponse atualizarAutorComLivroAssociado(Integer id, AutorRequest autorRequest);

    //Deletar autor e os livros dele associado
    LivroCategoriaAutorResponse deletarAutorELivrosAssociados(Integer id);


    //Adicionar autor do livro (verificar se o livro não terá nenhum autor relacionado para adicionar)




}
