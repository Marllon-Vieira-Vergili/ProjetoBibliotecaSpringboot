package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;

import java.util.List;

public interface AutorService {

    //Interface CRUD somente para as entidades


    //Criar
    Autor criarAutor(AutorRequest autorRequest);//associação bidirecional

    //Ler
   Autor encontrarAutorPorId(Integer id);
   List<Autor> encontrarTodosAutores();
    List<Autor> encontrarAutorPorNome(String nome); //e se eu tiver mais de um autor com o mesmo nome? vai virar lista...

    //Atualizar
    void atualizarAutor(Integer id, AutorRequest autorRequest);

    //Remover
    void deletarAutor(Integer id);
}
