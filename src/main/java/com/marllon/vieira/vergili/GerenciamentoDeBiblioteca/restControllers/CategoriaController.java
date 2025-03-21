package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restControllers;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.dto.CategoriaDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.CategoriaService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    //Funcionou
    @GetMapping(value = "/mostrartodascategorias")
    public List<CategoriaDTO> mostrarTodosCategorias(){
        return categoriaService.findAllCategorias();
    }


    @GetMapping(value = "/mostrarcategoria/{id}")
    public Optional<Categoria> mostrarCategoria(@PathVariable Integer id){
        return categoriaService.findCategoriaById(id);
    }



    @GetMapping(value = "/mostrarcategoriapelonome/{nome}")
    public Optional<CategoriaDTO> mostrarCategoriaPeloNome(@PathVariable String nome){
        return categoriaService.findCategoriaByNome(nome);
    }

    //Funcionou
    @PostMapping(value = "/adicionarcategoria")
    public void adicionarCategoria(@RequestBody CategoriaDTO categoria){
        categoriaService.saveCategoria(categoria);
    }

    //Funcionou
    @PutMapping(value = "/atualizarcategoria/{id}")
    public void atualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaDTO categoria){
        categoriaService.updateCategoria(id, categoria);
    }



    @DeleteMapping(value = "/deletarcategoria/{id}")
    public void removerCategoria(@PathVariable Integer id){
        categoriaService.deleteCategoria(id);

    }
}
