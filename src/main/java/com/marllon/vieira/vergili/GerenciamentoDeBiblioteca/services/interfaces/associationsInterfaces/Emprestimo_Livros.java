package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.EmprestimoELivrosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivrocomEmprestimosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.EmprestimoELivrosResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivrocomEmprestimosResponseDTO;


public interface Emprestimo_Livros {

    //Interface para criar métodos de associação entre emprestimo e livro, e livro com emprestimo, bidirecional

    //Associar Emprestimo a livros
    EmprestimoELivrosResponseDTO associarEmprestimoALivros(EmprestimoELivrosRequestDTO emprestimoELivrosRequestDTO);


    //Associar livros a emprestimos
    LivrocomEmprestimosResponseDTO associarLivrosAEmprestimo(LivrocomEmprestimosRequestDTO livrocomEmprestimosRequestDTO);
}
