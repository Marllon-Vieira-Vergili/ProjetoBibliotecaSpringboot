package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.controllers;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.AutorRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.AutorResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
Autor Controllers
 */


@RestController
@RequestMapping(name = "/autor")
public class AutorController {



    @Autowired
    private AutorService autorService;//Ja instancia a interface, pois ele ja vai pegar todos os métodos só para instanciar

    @GetMapping("/encontrarTodosAutores")
    public Iterable<AutorResponseDTO> encontrarTodosAutores(){
        return autorService.listarTodosOsAutores();
    }

    @GetMapping("/encontrarAutorPorId/{id}")
    public Optional<AutorResponseDTO> encontrarAutorPorId(@PathVariable Integer id){
        return autorService.listarAutorPorId(id);
    }

    @PostMapping("/adicionarAutor")
    public AutorResponseDTO adicionarAutor(@RequestBody AutorRequestDTO autorRequestDTO){
        return autorService.salvarAutor(autorRequestDTO);
    }

    @PutMapping("/atualizarAutor/{id}")
    public AutorResponseDTO atualizarAutor(@PathVariable Integer id, @RequestBody AutorRequestDTO autorRequestDTO){
        return autorService.atualizarAutor(id, autorRequestDTO);
    }

    @DeleteMapping("/deletarAutor/{id}")
    public void deletarAutor(@PathVariable Integer id){
        autorService.deletarAutor(id);
    }
}
