package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.util.List;

//Record para associar uma Categoria a livro(s).. o usuário irá digitar a id da categoria, e a id's dos livro(s)
//Esse formulário é o que o mesmo irá ver para adicionar os dados

public record CategoriaELivrosRequestDTO(
        @NotNull(message = "Id da Categoria não pode ser nulo!")
        @Size(min = 1) Integer categoriaId,
        @NotNull(message = "A lista deve conter pelo menos 1 id!")
        @Size(min = 1) List<Integer> livroId) {
}
