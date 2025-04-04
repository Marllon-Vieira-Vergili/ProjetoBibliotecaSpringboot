package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.CategoriaRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LeitorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;


import java.util.List;
import java.util.TreeSet;

public interface CategoriaService {

    //Interface CRUD somente para as entidades


    //Criar
    Categoria criarCategoria(CategoriaRequest categoriaRequest);//associação bidirecional

    //Ler
    Categoria encontrarCategoriaPorId(Integer id);
    TreeSet<Categoria> encontrarTodasCategorias();
    Categoria encontraCategoriaPorNome(String nome);

    //Atualizar
    void atualizarCategoria(Integer id, CategoriaRequest categoriaRequest);

    //Remover
    void deletarCategoria(Integer id);
}


