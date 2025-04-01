package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.controllers.associations;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.AutorELivrosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroEAutorRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.AutorELivrosResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroEAutorResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces.Autor_Livros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/autor-livro")
public class LivoAndAutor {


    @Autowired
    private Autor_Livros autorLivros;


    @PostMapping("/associarAutorLivro")
    public AutorELivrosResponseDTO associarAutorALivro
            (@RequestBody AutorELivrosRequestDTO autorELivrosRequestDTO){
        return autorLivros.associarAutorALivros(autorELivrosRequestDTO);
    }

    @PostMapping("/associarLivroAoAutor")
    public List<LivroEAutorResponseDTO> associarLivrosAoAutor(@RequestBody LivroEAutorRequestDTO livroEAutorRequestDTO){
        return autorLivros.associarLivrosAUmAutor(livroEAutorRequestDTO);
    }

    @GetMapping("/encontrarAutorELivrosAssociados/{id}")
    public List<AutorELivrosResponseDTO> encontrarAutorELivrosAssociados
            (@PathVariable Integer id){
        return autorLivros.encontrarAutorESeusLivrosAssociados(id);
    }
}
