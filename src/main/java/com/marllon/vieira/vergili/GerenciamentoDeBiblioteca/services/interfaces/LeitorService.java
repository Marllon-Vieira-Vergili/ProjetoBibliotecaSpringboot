package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.LeitorRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LeitorResponseDTO;

public interface LeitorService {

    //Ler
    Iterable<LeitorResponseDTO> listarTodosOsLeitores();
    LeitorResponseDTO listarLeitorPorId(Integer id);


    //Salvar
    LeitorResponseDTO salvarLeitor(LeitorRequestDTO requestDTO);

    //Atualizar
    LeitorResponseDTO atualizarLeitor(Integer id, LeitorRequestDTO leitorRequestDTO);

    //Remover
    void removerLeitor(Integer id);
}
