package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restController;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation.LivroCategoriaAutorRequest;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LivroRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LivroAssociationsResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LivroCategoriaAutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.LivroAssociationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/livro")
@Tag(name = "Livro", description = "Endpoints para gerenciamento de Livros")
public class LivroController {

    @Autowired
    private LivroAssociationService livroAssociationService;


    @PostMapping(value = "/adicionarLivroComAutor")
    public LivroCategoriaAutorResponse adicionarLivroComAutor(@RequestBody LivroCategoriaAutorRequest livroCategoriaAutorRequest) {
        return livroAssociationService.criarLivroComAutor(livroCategoriaAutorRequest);
    }

    @GetMapping(value = "/encontrarLivroPorId/{id}")
    public LivroAssociationsResponse encontrarLivroPorId(@PathVariable Integer id) {
        return livroAssociationService.encontarUmLivro(id);
    }

    @GetMapping(value = "/encontrarTodosLivros")
    public List<LivroAssociationsResponse> encontrarTodosLivros() {
        return livroAssociationService.encontrarTodosLivros();
    }

    @PutMapping(value = "/atualizarLivro/{id}")
    public LivroAssociationsResponse atualizarLivro(@PathVariable Integer id, @RequestBody LivroRequest livroRequest) {
        return livroAssociationService.atualizarLivro(id, livroRequest);
    }

    @DeleteMapping(value = "/deletarLivro/{id}")
    public LivroAssociationsResponse removerLivro(@PathVariable Integer id) {
        return livroAssociationService.removerLivro(id);
    }

}