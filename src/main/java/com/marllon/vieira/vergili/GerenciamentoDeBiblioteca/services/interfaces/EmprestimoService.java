package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.EmprestimoRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.EmprestimoResponseDTO;

import java.util.Optional;

public interface EmprestimoService {


    //Ler
    Iterable<EmprestimoResponseDTO> listarTodosOsEmprestimos();
    EmprestimoResponseDTO encontrarEmprestimoPorId(Integer id);

    //Salvar
    EmprestimoResponseDTO salvarEmprestimo(EmprestimoRequestDTO emprestimo);

    //Atualizar
    EmprestimoResponseDTO atualizarEmprestimo(Integer id, EmprestimoRequestDTO emprestimoRequestDTO);

    //Deletar
    void removerEmprestimo(Integer id);

}
