package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import java.util.List;

public record LeitorResponseDTO(Integer id, String nome, String sobrenome, String email,
                                Integer idade,List<Livro> listaLivrosRelacionadosAoLeitor,
                                List<Emprestimo> listaEmprestimosRelacionadosAoLeitor) {
}
