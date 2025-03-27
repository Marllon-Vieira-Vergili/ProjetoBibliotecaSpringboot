package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.LivroRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponseDTO;

public interface LivroService {

    //Ler
    Iterable<LivroResponseDTO> listarTodosLivros();
    LivroResponseDTO encontrarLivroPorId(Integer id);

    //Salvar
    LivroResponseDTO salvarLivro(LivroRequestDTO livroRequestDTO);

    //Atualizar
    LivroResponseDTO atualizarLivro(Integer id, LivroRequestDTO livroRequestDTO);

    //Deletar
    void deletarLivro(Integer id);
}
