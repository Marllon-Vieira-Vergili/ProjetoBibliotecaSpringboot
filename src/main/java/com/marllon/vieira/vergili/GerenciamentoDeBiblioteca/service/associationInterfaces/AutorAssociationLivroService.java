package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation.AutorRequestComLivro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.AutorComLivroResponse;

import java.util.List;


public interface AutorAssociationLivroService {

    //Criar autor e os livros dele associado
    AutorComLivroResponse criarAutorComLivro(AutorRequestComLivro autorRequestComLivro); //usado na Api Rest

    //Ler autor e os livros dele associado
    AutorComLivroResponse lerAutorPorId(Integer id);
    List<AutorComLivroResponse> lerTodosAutores();

    //Atualizar autor e mostrar os livros dele associado
    AutorComLivroResponse atualizarAutorComLivroAssociado(Integer id, AutorRequest autorRequest);

    //Deletar autor e os livros dele associado
    AutorComLivroResponse deletarAutorELivrosAssociados(Integer id);




}
