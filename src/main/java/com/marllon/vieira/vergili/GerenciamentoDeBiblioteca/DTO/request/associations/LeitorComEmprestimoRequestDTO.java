package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;

import java.util.List;

public record LeitorComEmprestimoRequestDTO(List<Emprestimo> listaEmprestimosRelacionadosAoLeitor) {
}
