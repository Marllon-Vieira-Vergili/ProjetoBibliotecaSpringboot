package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

//Record para associar um autor a vários livro(s).. o usuário irá digitar a id do autor, e a id's dos livro(s)

public record AutorELivrosRequestDTO(@NotNull(message = "Id do autor não pode ser nulo!")
                                     Integer autorId,
                                     @NotNull(message = "Id dos livros não pode ser nulo!")
                                     @Size(min = 1, message = "A lista de livros deve conter pelo menos 1 caractere!")
                                     List<Integer> livrosIds) {
}
