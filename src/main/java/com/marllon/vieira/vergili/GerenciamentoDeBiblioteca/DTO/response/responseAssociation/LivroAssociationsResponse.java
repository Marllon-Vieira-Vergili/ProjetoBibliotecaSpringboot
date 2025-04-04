package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.*;

import java.util.List;

//Resposta de retorno do livro, com as associacoes de response das outras entidades, muitas para muitas
//Muitos Livros para um autor associado,
//Muitas categorias para um livro associado,
//Muitos emprestimos para muitos livros,
//Muitos leitores para muitos livros
public record LivroAssociationsResponse(List<LivroResponse> ListaLivros,
                                        AutorResponse AutorResponsavel,
                                        CategoriaResponse Categoria,
                                        EmprestimoResponse EmprestimoAssociado,
                                        LeitorResponse LeitorAssociado) {
}