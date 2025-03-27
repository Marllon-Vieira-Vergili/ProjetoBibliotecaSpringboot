package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces;


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
    AutorResponseDTO salvarAutor(AutorRequestDTO autor);


    //Atualizar
    AutorResponseDTO atualizarAutor(Integer id, AutorRequestDTO autor); //Vai me retornar a resposta do corpo
    //do autor, para que o usuário veja a mudança


    //Deletar
    void deletarAutor(Integer id);

}
