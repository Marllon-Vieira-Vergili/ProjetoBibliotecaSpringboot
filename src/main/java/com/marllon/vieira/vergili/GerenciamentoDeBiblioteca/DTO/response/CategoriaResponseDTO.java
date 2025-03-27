package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import java.util.List;


//Classse Record para receber  dados de requisição.. será enviado até as associações no corpo de resposta

public record CategoriaResponseDTO(Integer id, String nomeCategoria, List<Livro> listaLivrosRelacionados) {
}

