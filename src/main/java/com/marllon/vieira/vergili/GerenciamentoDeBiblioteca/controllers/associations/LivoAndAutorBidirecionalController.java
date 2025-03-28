package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.controllers.associations;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.AutorELivrosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroEAutorRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.AutorELivrosResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroEAutorResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces.Autor_Livros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LivoAndAutorBidirecionalController {


    @Autowired
    private Autor_Livros autorLivros;


    @PostMapping("/associarAutorLivro")
    public AutorELivrosResponseDTO associarAutorALivro
            (@RequestBody AutorELivrosRequestDTO autorELivrosRequestDTO){
        return autorLivros.associarAutorALivros(autorELivrosRequestDTO);
    }

    @PostMapping("/AssociarLivroAoAutor")
    public List<LivroEAutorResponseDTO> associarLivrosAoAutor(@RequestBody LivroEAutorRequestDTO livroEAutorRequestDTO){
        return autorLivros.associarLivrosAUmAutor(livroEAutorRequestDTO);
    }
}
