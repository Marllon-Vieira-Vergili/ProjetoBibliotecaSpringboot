package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restController;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroRequestComAutor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroComAutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.LivroAndAssociationService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.LivroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/livro")
@Tag(name = "Livro e suas Associações")
public class LivroController {

    @Autowired
    private LivroAndAssociationService livroAndAssociationService;

/* esse método foi só pra testar se ta adicionando somente um livro
    @PostMapping("/adicionarLivro")
    public void adicionarLivro(@RequestBody LivroRequest livroRequest){
        livroService.criarLivro(livroRequest);
    }

 */

    @PostMapping(value = "adicionarLivroComAutor")
    public LivroComAutorResponse adicionarLivroComAutor(@RequestBody LivroRequestComAutor livroRequestComAutor){
        return livroAndAssociationService.criarLivroComAutor(livroRequestComAutor);
    }


}