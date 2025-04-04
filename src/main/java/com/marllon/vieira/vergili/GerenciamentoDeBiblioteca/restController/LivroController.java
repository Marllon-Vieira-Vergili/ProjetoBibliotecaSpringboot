package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restController;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation.LivroRequestComAutor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LivroComAutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.LivroAndAssociationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/livro")
@Tag(name = "Livro", description = "Endpoints para gerenciamento de Livros")
public class LivroController {

    @Autowired
    private LivroAndAssociationService livroAndAssociationService;


    @PostMapping(value = "/adicionarLivroComAutor")
    public LivroComAutorResponse adicionarLivroComAutor(@RequestBody LivroRequestComAutor livroRequestComAutor){
        return livroAndAssociationService.criarLivroComAutor(livroRequestComAutor);
    }





}