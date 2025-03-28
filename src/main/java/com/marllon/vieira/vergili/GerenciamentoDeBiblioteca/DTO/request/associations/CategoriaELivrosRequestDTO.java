package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;

import java.util.List;

//Record para associar uma Categoria a livro(s).. o usuário irá digitar a id da categoria, e a id's dos livro(s)

public record CategoriaELivrosRequestDTO(List<Livro> listaLivrosRelacionados) {
}
