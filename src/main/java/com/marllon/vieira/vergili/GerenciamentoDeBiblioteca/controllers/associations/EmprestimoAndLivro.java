package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.controllers.associations;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.EmprestimoELivrosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivrocomEmprestimosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.EmprestimoELivrosResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroComEmprestimoResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces.Emprestimo_Livros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/emprestimo-livro")
public class EmprestimoAndLivro {



    @Autowired
    private Emprestimo_Livros emprestimoLivros;


    @PostMapping("/associar-emprestimo-livro")
    public EmprestimoELivrosResponseDTO associarEmprestimosLivros(@RequestBody  EmprestimoELivrosRequestDTO emprestimoELivrosRequestDTO){
        return emprestimoLivros.associarEmprestimoALivros(emprestimoELivrosRequestDTO);
    }

    @PostMapping("/associar-livro-a-emprestimo")
    public LivroComEmprestimoResponseDTO associarLivroComEmprestimo(@RequestBody LivrocomEmprestimosRequestDTO livroComEmprestimoRequestDTO){
        return emprestimoLivros.associarLivrosAEmprestimo(livroComEmprestimoRequestDTO);
    }

    @GetMapping(value = "/encontrar-livro-com-emprestimo/{id}")
    public LivroComEmprestimoResponseDTO encontrarLivroComEmprestimo(@PathVariable Integer id){
        return emprestimoLivros.encontrarLivroComEmprestimo(id);
    }
}
