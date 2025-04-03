package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.AutorRequestComLivro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.AutorComLivroResponse;

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
