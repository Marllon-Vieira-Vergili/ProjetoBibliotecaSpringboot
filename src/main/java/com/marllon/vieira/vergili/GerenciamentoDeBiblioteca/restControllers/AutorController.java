package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restControllers;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }



    @GetMapping(value = "/mostrartodosautores")
    public List<Autor> mostrarTodosAutores(){
        return autorService.findAllAutor();
    }


    @GetMapping(value = "/mostrarautor/{id}")
    public Optional<Autor> mostrarAutor(@PathVariable Integer id){
        return autorService.findAutorById(id);
    }


    @GetMapping(value = "/mostrarautorporemail/{email}")
    public Optional<Autor> mostrarAutorPorEmail(@PathVariable String email){
        return autorService.findAutorByEmail(email);
    }






    @PostMapping(value = "/adicionarautor")
    public void adicionarAutor(@RequestBody Autor autor){
        autorService.saveAutor(autor);
    }



    @PutMapping(value = "/atualizarautor/{id}")
    public void atualizarAutor(@PathVariable Integer id, @RequestBody Autor autor){
        autorService.updateAutor(id, autor);
    }

    @DeleteMapping(value = "/deletarautor/{id}")
    public void removerAutor(@PathVariable Integer id){
        autorService.deleteAutor(id);
    }
}
