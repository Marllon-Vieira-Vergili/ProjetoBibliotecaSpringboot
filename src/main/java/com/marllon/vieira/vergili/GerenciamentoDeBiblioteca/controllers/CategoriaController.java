package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.controllers;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.CategoriaRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.CategoriaResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {


    @Autowired
    private CategoriaService categoriaService;//Ja instancia a interface, pois ele ja vai pegar todos os métodos só para instanciar


    @GetMapping(value = "/encontrarTodasCategorias")
    public Iterable<CategoriaResponseDTO> encontrarTodasCategorias(){
        return categoriaService.listarTodasCategorias();

    }

    @GetMapping(value = "/encontrarCategoriaPorId/{id}")
    public CategoriaResponseDTO encontrarCategoriaPorId(@PathVariable Integer id){
        return categoriaService.encontrarCategoriaPorId(id);
    }

    @PostMapping(value = "/salvarCategoria")
    public CategoriaResponseDTO salvarCategoria(@RequestBody CategoriaRequestDTO categoria){
        return categoriaService.salvarCategoria(categoria);
    }

    @PutMapping(value = "/atualizarCategoria/{id}")
    public CategoriaResponseDTO atualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaRequestDTO categoria){
        return categoriaService.atualizarCategoria(id, categoria);
    }

    @DeleteMapping(value = "/deletarCategoria/{id}")
    public void deletarCategoria(@PathVariable Integer id){
        categoriaService.deletarCategoria(id);
    }
}
