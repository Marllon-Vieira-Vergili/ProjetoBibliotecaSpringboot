package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.EmprestimoELeitoresRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LeitorComEmprestimoRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.EmprestimoELeitoresResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LeitorComEmprestimoResponseDTO;


public interface Emprestimo_Leitores {

    //Interface para criar métodos de associação entre Emprestimo e leitores, e leitores com empréstimo, bidirecional


    //Associar Emprestimo a leitores
    EmprestimoELeitoresResponseDTO associarEmprestimoALeitor(EmprestimoELeitoresRequestDTO emprestimoELeitoresRequestDTO);


    //Associar leitores a Empréstimos
    LeitorComEmprestimoResponseDTO associarLeitorAEmprestimo(LeitorComEmprestimoRequestDTO leitorComEmprestimoRequestDTO);
}
