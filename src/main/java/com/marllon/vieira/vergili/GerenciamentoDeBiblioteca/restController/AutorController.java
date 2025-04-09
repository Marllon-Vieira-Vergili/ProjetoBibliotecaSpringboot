package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restController;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation.LivroCategoriaAutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.AutorRequest;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LivroCategoriaAutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.AutorAssociationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/autor")
@Tag(name = "Autor", description = "Endpoints para gerenciamento de Autores")
public class AutorController {

    @Autowired
    private AutorAssociationService autorAssociationService;

    /*
    //Método só para testar se está adicionando autor certinho, comentado...
    @PostMapping("/adicionarAutor")
    public void adicionarAutor(@RequestBody AutorLivroRequest autorRequest){
        autorAssociationService.criarAutor(autorRequest);
    }
     */

    @PostMapping(value = "/adicionarAutorComLivro")
    public LivroCategoriaAutorResponse adicionarAutorComLivros(@RequestBody LivroCategoriaAutorRequest livroCategoriaAutorRequest){
        return autorAssociationService.criarAutorComLivro(livroCategoriaAutorRequest);
    }

    @GetMapping(value = "/listarAutorELivro/{id}")
    public LivroCategoriaAutorResponse listarAutorComLivro(@PathVariable Integer id){
        return autorAssociationService.lerAutorPorId(id);
    }

    @GetMapping(value = "listarTodosAutores")
    public List<LivroCategoriaAutorResponse> listarTodosLivros(){
        return autorAssociationService.lerTodosAutores();
    }

    @PutMapping(value = "/atualizarAutor/{id}")
    public LivroCategoriaAutorResponse atualizarAutor(@PathVariable Integer id, @RequestBody AutorRequest autorRequest){
        return autorAssociationService.atualizarAutorComLivroAssociado(id, autorRequest);
    }

    @DeleteMapping(value = "/deletarAutor/{id}")
    public LivroCategoriaAutorResponse removerAutor(@PathVariable Integer id){
        return autorAssociationService.deletarAutorELivrosAssociados(id);
    }

}
