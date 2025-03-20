package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restControllers;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }


    @GetMapping(value = "/mostrartodoslivros")
    public List<Livro> mostrarTodosLivros(){
        return livroService.findAllivro();
    }


    @GetMapping(value = "/mostrarlivro/{id}")
    public Optional<Livro> mostrarLivro(@PathVariable Integer id){
        return livroService.findLivroById(id);
    }



    @GetMapping(value = "/mostrarlivropelonome/{nome}")
    public Optional<Livro> mostrarLivroPeloNome(@PathVariable String nome){
        return livroService.encontrarLivroPeloTitulo(nome);
    }





    @PostMapping(value = "/adicionarlivro")
    public void adicionarLivro(@RequestBody Livro livro){
        livroService.saveLivro(livro);
    }


    @PutMapping(value = "/atualizarlivro/{id}")
    public void atualizarLivro(@PathVariable Integer id, @RequestBody Livro livro){
        livroService.updateLivro(id, livro);
    }



    @DeleteMapping(value = "/deletarlivro/{id}")
    public void removerLivro(@PathVariable Integer id){
        livroService.deleteLivro(id);

    }
}
