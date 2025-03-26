package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.controllers;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.AutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
Autor Controllers
 */


@RestController
@RequestMapping("/autores")
public class AutorController {



    @Autowired
    private AutorService autorService;//Ja instancia a interface, pois ele ja vai pegar todos os métodos só para instanciar

    @GetMapping("/encontrarTodosAutores")
    public String encontrarTodosAutores(){
        return autorService.listarTodosOsAutores().toString();
    }
}
