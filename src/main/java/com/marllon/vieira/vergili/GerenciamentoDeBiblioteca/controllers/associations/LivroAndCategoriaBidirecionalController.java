package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.controllers.associations;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.CategoriaELivrosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroECategoriaRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.CategoriaELivrosResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroECategoriaResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces.Categoria_Livros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categoriaLivros")
public class LivroAndCategoriaBidirecionalController {

    @Autowired
    private Categoria_Livros categoriaLivros;


    @PostMapping(value = "/associarCategoriaAosLivros") //Pode também opcionalmente retornar um response entity ResponseEntity
    public CategoriaELivrosResponseDTO associarCategoriaALivros
            (@RequestBody CategoriaELivrosRequestDTO categoriaELivrosRequestDTO){
        return categoriaLivros.associarCategoriaALivro(categoriaELivrosRequestDTO);
    }

    @PostMapping(value = "/associarLivrosACategoria")
    public List<LivroECategoriaResponseDTO> associarLivrosACategoria
            (@RequestBody LivroECategoriaRequestDTO livroECategoriaRequestDTO){
        return categoriaLivros.associarLivroACategoria(livroECategoriaRequestDTO);

    }

}
