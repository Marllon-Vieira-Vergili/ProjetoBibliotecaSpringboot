package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;


import java.util.List;

public record LivroResponseDTO(Integer id, String nome, Integer anoLancamento, Autor autorRelacionado,
                               List<Leitor> listaLeitoresRelacionados,List<Emprestimo> listaEmprestimosRelacionados,
                               List<Categoria> listaLivrosComCategoria) {
}


