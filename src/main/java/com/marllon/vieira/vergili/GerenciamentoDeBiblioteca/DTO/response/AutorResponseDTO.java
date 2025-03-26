package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response;


//Classse Record para receber  dados de requisição.. será enviado até as associações no corpo de resposta


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import java.util.List;

public record AutorResponseDTO(Integer id, String nome, String email, String telefone,
                               String cidade, List<Livro> listaLivrosDosAutores) {
}
