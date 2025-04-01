package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LeitorComLivroRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroComLeitorRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LeitorComLivroResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroComLeitorResponseDTO;

public interface Leitores_Livros {

    //Interface para criar métodos de associação entre leitor e livro, e livro leitor, bidirecional


    //Associar Leitores a livros
    LeitorComLivroResponseDTO associarLeitorAUmLivro(LeitorComLivroRequestDTO leitorComLivroRequestDTO);


    //Associar Livros a leitores
    LivroComLeitorResponseDTO associarLivroAUmLeitor(LivroComLeitorRequestDTO livroComLeitorRequestDTO);

    //Encontrar livros relacionados com leitores
    LeitorComLivroResponseDTO encontrarLeitorComLivroRelacionado(Integer id);
}
