package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.AutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.LivroResponse;

import java.util.List;


//Resposta do relacionamento um autor para Vàrios livros
public record AutorComLivroResponse(AutorResponse Autor, List<LivroResponse> Livros) {
}

