package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restController;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.LivroRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.AutorComLivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autor")

public class AutorController {

    @Autowired
    private AutorService autorService;


    @PostMapping("/adicionarAutorComLivro")
    public void adicionarAutorComLivro(@RequestBody AutorRequest autorRequest){
        autorService.criarAutor(autorRequest);
    }



}
