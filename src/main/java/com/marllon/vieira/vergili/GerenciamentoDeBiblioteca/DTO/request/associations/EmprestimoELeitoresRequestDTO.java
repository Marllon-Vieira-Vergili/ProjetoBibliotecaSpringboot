package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;

import java.time.LocalDate;
import java.util.List;

public record EmprestimoELeitoresRequestDTO(List<Leitor> listaLeitoresComEmprestimos) {
}
