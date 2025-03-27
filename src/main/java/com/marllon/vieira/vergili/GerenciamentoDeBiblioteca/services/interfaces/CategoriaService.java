package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.CategoriaRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.CategoriaResponseDTO;

public interface CategoriaService {


    //Ler
    Iterable <CategoriaResponseDTO> listarTodasCategorias();
    CategoriaResponseDTO encontrarCategoriaPorId(Integer id); //optei por nao usar optional nesse

    //Salvar
    CategoriaResponseDTO salvarCategoria(CategoriaRequestDTO categoria);


    //Atualizar
    CategoriaResponseDTO atualizarCategoria(Integer id, CategoriaRequestDTO categoria);

    //Deletar
    void deletarCategoria(Integer id);
}
