package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;

import java.util.List;

public record LeitorComLivroRequestDTO(List<Livro> listaLivrosRelacionadosAoLeitor) {
}
