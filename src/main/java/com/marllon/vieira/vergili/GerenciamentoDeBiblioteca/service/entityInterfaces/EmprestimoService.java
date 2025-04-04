package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.EmprestimoRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;

import java.util.List;

public interface EmprestimoService {

    //Interface CRUD somente para as entidades


    //Criar
    Emprestimo criarEmprestimo(EmprestimoRequest emprestimoRequest);//associação bidirecional

    //Ler
    Emprestimo encontrarEmprestimoPorId(Integer id);
    List<Emprestimo> encontrarTodosEmprestimos();


    //Atualizar
    void atualizarEmprestimo(Integer id, EmprestimoRequest emprestimoRequest);


    //Remover
    void deletarEmprestimo(Integer id);
}
