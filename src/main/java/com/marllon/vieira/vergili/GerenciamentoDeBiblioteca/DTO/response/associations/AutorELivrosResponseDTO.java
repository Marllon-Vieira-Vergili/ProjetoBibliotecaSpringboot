package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponseDTO;


import java.util.Iterator;
import java.util.List;

public record AutorELivrosResponseDTO(Integer id, String nome, String email, String cidade, String telefone,
                                      List<LivroResponseDTO> listaLivrosDosAutores) {
}
