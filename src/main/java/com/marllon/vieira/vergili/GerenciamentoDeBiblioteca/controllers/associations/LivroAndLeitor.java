package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.controllers.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LeitorComLivroRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroComLeitorRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LeitorComLivroResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroComLeitorResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces.Leitores_Livros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/livro-e-leitor")
public class LivroAndLeitor {

    @Autowired
    private Leitores_Livros leitoresLivros;


    @PostMapping(value = "/associar-leitor-livro")
    public LeitorComLivroResponseDTO associarLeitorLivro(@RequestBody LeitorComLivroRequestDTO leitorComLivroRequestDTO){
        return leitoresLivros.associarLeitorAUmLivro(leitorComLivroRequestDTO);
    }

    @PostMapping(value = "/associar-livro-leitor")
    public LivroComLeitorResponseDTO associaLivroComLeitor(@RequestBody LivroComLeitorRequestDTO livroComLeitorRequestDTO){
        return leitoresLivros.associarLivroAUmLeitor(livroComLeitorRequestDTO);
    }

    @GetMapping(value = "/encontrar-leitor-com-livro/{id}")
    public LeitorComLivroResponseDTO encontrarLeitorComLivro(@PathVariable Integer id){
        return leitoresLivros.encontrarLeitorComLivroRelacionado(id);
    }
}
