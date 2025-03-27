package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.controllers;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.LivroRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/livro")
public class LivroController {


    @Autowired
    private LivroService livroService;


    @GetMapping(value = "/mostrarTodosLivros")
    public Iterable<LivroResponseDTO> mostrarTodosOsLivros(){
        return livroService.listarTodosLivros();
    }

    @GetMapping(value = "mostrarLivroPorId/{id}")
    public LivroResponseDTO mostrarLivroPorId(Integer id){
        return livroService.encontrarLivroPorId(id);
    }


    @PostMapping(value = "/adicionarLivro")
    public LivroResponseDTO adicionarLivro(@RequestBody LivroRequestDTO livroRequestDTO){
        return livroService.salvarLivro(livroRequestDTO);
    }

    @PutMapping(value = "/atualizarLivro/{id}")
    public LivroResponseDTO atualizarLivro(@PathVariable Integer id, @RequestBody LivroRequestDTO livroRequestDTO){
        return livroService.atualizarLivro(id, livroRequestDTO);
    }

    @DeleteMapping(value = "/deletarLivro/{id}")
    public void deletarLivroPorId(@PathVariable Integer id){
        livroService.deletarLivro(id);
    }
}
