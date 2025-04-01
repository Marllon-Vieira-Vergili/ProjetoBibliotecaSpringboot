package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record EmprestimoELivrosRequestDTO(Integer emprestimoId, Integer livroId) {
}
