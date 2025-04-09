package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation.LivroCategoriaAutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LivroRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LivroAssociationsResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LivroCategoriaAutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;

import java.util.List;

public interface LivroAssociationService {

    //Criar
    LivroCategoriaAutorResponse criarLivroComAutor(LivroCategoriaAutorRequest livroCategoriaAutorRequest); //usado na Api Rest

    //Ler
    LivroAssociationsResponse encontarUmLivro(Integer id);
    List<LivroAssociationsResponse> encontrarTodosLivros();

    //Atualizar
    LivroAssociationsResponse atualizarLivro(Integer id, LivroRequest livrorequest);

    //Deletar
    LivroAssociationsResponse removerLivro(Integer id);




    //adicionar emprestimo ao livro

    //adicionar leitor ao livro


    //remover emprestimo de livro

    //remover leitor associado de livro

}
