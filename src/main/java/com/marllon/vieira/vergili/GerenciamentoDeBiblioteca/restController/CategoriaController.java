package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restController;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.CategoriaRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.CategoriaResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.TreeSet;

@RestController
@RequestMapping(value = "/api/categoria")
@Tag(name = "Categoria", description = "Endpoints para gerenciamento de Categorias")
public class CategoriaController {


    @Autowired
    private CategoriaService categoriaService;


    @PostMapping(value = "/criarCategoria")
    public Categoria criarCategoria(@RequestBody CategoriaRequest categoriaRequest){
        return categoriaService.criarCategoria(categoriaRequest);
    }

    @GetMapping("/mostrarcategoria{id}")
    public Categoria mostrar(@PathVariable Integer id){
        return categoriaService.encontrarCategoriaPorId(id);
    }

    @GetMapping("/mostrartodas")
    public TreeSet<Categoria> mostrar(){
        return categoriaService.encontrarTodasCategorias();
    }

    @PutMapping("/atualiza/{id}")
    public void atualiza(@PathVariable Integer id, CategoriaRequest categoriaRequest){
        categoriaService.atualizarCategoria(id, categoriaRequest);
    }

    @DeleteMapping("/deleta/{id}")
    public void deleta(@PathVariable Integer id){
        categoriaService.deletarCategoria(id);
    }
}
