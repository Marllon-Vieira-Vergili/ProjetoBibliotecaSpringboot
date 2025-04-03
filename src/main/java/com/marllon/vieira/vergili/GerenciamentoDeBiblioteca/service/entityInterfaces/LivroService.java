package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.LivroRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
public interface LivroService {



    //criar somente livro
    Livro criarLivro(LivroRequest livroRequest); //associação bidirecional


}
