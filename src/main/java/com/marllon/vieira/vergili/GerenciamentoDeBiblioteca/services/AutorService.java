package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.AutorRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.AutorResponseDTO;


import java.util.Optional;

/*
Interface que será instanciada nos nossos Controllers..ou Rest Controllers
 */


//Lógicas dos DTOs.. tanto para enviar dados para o cabeçalho do autor, quanto para receber dados do cabeçalho do autor

public interface AutorService {

    //Ler
    Iterable<AutorResponseDTO> listarTodosOsAutores();
    Optional<AutorResponseDTO> listarAutorPorId(Integer id);

    //Salvar
    AutorRequestDTO salvarAutor(AutorRequestDTO autor);


    //Atualizar
    void atualizarAutor(Integer id, AutorRequestDTO autor);


    //Deletar
    void deletarAutor(Integer id);

}
