package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import java.time.LocalDate;
import java.util.List;

public record EmprestimoResponseDTO(Integer id, LocalDate dataEmprestimo, LocalDate dataDevolucao,
                                    boolean estaEmprestado) {
}

