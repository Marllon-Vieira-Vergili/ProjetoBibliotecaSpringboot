package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.AutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponse;

import java.util.List;

//Funcionou para adicionar autor ao livro
public record AutorComLivroResponse(AutorResponse Autor, List<LivroResponse> Livros) {
}

