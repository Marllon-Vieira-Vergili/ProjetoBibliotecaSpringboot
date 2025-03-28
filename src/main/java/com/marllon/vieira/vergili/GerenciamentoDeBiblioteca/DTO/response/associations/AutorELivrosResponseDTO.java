package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;


import java.util.List;

public record AutorELivrosResponseDTO(Integer id, String nome, String email, String telefone,
                                      String cidade, List<Livro> listaLivrosDosAutores) {
}
