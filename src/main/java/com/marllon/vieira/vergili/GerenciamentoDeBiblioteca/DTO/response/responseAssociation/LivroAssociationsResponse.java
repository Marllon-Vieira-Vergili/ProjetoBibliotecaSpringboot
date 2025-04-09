package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.*;

import java.util.List;

//Resposta de retorno do livro, com as associacoes de response das outras entidades, muitas para muitas
//Muitos Livros para um autor associado,
//Muitas categorias para um livro associado,
//Muitos emprestimos para muitos livros,
//Muitos leitores para muitos livros
//Para os métodos do livro, respostas
public record LivroAssociationsResponse(List<LivroResponse> ListaLivros,
                                        AutorResponse AutorResponsavel,
                                        List<CategoriaResponse> Categorias,
                                        List<EmprestimoResponse> EmprestimosAssociados,
                                        List<LeitorResponse> LeitoresAssociados) {
}