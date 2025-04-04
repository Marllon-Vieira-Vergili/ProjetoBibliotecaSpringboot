package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restController;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation.AutorRequestComLivro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.AutorComLivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.AutorAssociationLivroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/autor")
@Tag(name = "Autor", description = "Endpoints para gerenciamento de Autores")
public class AutorController {

    @Autowired
    private AutorAssociationLivroService autorAssociationLivroService;

    /*
    //Método só para testar se está adicionando autor certinho, comentado...
    @PostMapping("/adicionarAutor")
    public void adicionarAutor(@RequestBody AutorRequestComLivro autorRequest){
        autorAssociationLivroService.criarAutor(autorRequest);
    }
     */

    @PostMapping(value = "/adicionarAutorComLivro")
    public AutorComLivroResponse adicionarAutorComLivros(@RequestBody AutorRequestComLivro autorRequestComLivro){
        return autorAssociationLivroService.criarAutorComLivro(autorRequestComLivro);
    }

    @GetMapping(value = "/listarAutorELivro/{id}")
    public AutorComLivroResponse listarAutorComLivro(@PathVariable Integer id){
        return autorAssociationLivroService.lerAutorPorId(id);
    }

    @GetMapping(value = "listarTodosAutores")
    public List<AutorComLivroResponse> listarTodosLivros(){
        return autorAssociationLivroService.lerTodosAutores();
    }

    @PutMapping(value = "/atualizarAutor/{id}")
    public AutorComLivroResponse atualizarAutor(@PathVariable Integer id, @RequestBody AutorRequest autorRequest){
        return autorAssociationLivroService.atualizarAutorComLivroAssociado(id, autorRequest);
    }

    @DeleteMapping(value = "/deletarAutor/{id}")
    public AutorComLivroResponse removerAutor(@PathVariable Integer id){
        return autorAssociationLivroService.deletarAutorELivrosAssociados(id);
    }

}
