package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restController;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.CategoriaRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.CategoriaLivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.CategoriaResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.CategoriaAssociationService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categoria")
@Tag(name = "Categoria", description = "Endpoints para gerenciamento de Categorias")
public class CategoriaController {



    @Autowired
    private CategoriaAssociationService categoriaAssociationService;

/*
    @PostMapping(value = "/criarCategoria")
    public Categoria criarCategoria(@RequestBody CategoriaRequest categoriaRequest){
        return categoriaService.criarCategoria(categoriaRequest);
    }

    @GetMapping("/mostrarcategoria{id}")
    public Categoria mostrar(@PathVariable Integer id){
        return categoriaService.encontrarCategoriaPorId(id);
    }

    @GetMapping("/mostrartodas")
    public List<Categoria> mostrar(){
        return categoriaService.encontrarTodasCategorias();
    }

    @PutMapping("/atualizarCategoria/{id}")
    public void atualiza(@PathVariable Integer id, @RequestBody  CategoriaRequest categoria){
        categoriaService.atualizarCategoria(id, categoria);
    }

    @DeleteMapping("/deleta/{id}")
    public void deleta(@PathVariable Integer id){
        categoriaService.deletarCategoria(id);
    }


 */


    @PostMapping("/criarNovaCategoria")
    public CategoriaResponse criarNovaCategoria(@RequestBody CategoriaRequest categoriaRequest){
        return categoriaAssociationService.criarCategoria(categoriaRequest);
    }

    @GetMapping("/mostrarcategoria/{id}")
    public CategoriaLivroResponse mostrarCategoriaELivros(@PathVariable Integer id){
        return categoriaAssociationService.encontrarUmaCategoriaELivros(id);
    }



    @GetMapping("/mostrarTodasCategorias")
    public List<CategoriaLivroResponse> mostrarTodasCategoriasELivros(){
        return categoriaAssociationService.encontrarTodasCategoriasELivrosAssociados();
    }


    @PutMapping("/atualizarCategoria/{id}")
    public CategoriaLivroResponse atualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaRequest categoriaRequest){
        return categoriaAssociationService.atualizarCategoria(id, categoriaRequest);
    }

    @DeleteMapping("/deletarCategoria/{id}")
    public CategoriaLivroResponse deletarCategoria(@PathVariable Integer id){
        return categoriaAssociationService.removerCategoriaEdesassociarDosLivros(id);
    }

    @PostMapping("/adicionarCategoriaAoLivro/{categoriaId}/{livroId}")
    public CategoriaLivroResponse adicionarCategoriaAoLivro(@PathVariable Integer categoriaId, @PathVariable
    Integer livroId){
        return categoriaAssociationService.adicionarCategoriaAoLivro(categoriaId,livroId);
    }

    @DeleteMapping("/removerCategoriaDoLivro/{categoriaId}/{livroId}")
    public CategoriaLivroResponse removerCategoriaDoLivro(@PathVariable Integer categoriaId, @PathVariable Integer livroId){
        return categoriaAssociationService.removerCategoriaAoLivro(livroId,categoriaId);
    }

}
