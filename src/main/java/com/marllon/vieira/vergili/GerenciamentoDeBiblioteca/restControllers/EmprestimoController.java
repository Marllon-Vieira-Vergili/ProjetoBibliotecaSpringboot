package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restControllers;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }



    @GetMapping(value = "/mostrartodosemprestimos")
    public List<Emprestimo> mostrarTodosEmprestimos(){
        return emprestimoService.findAllEmprestimo();
    }


    @GetMapping(value = "/mostraremprestimo/{id}")
    public Optional<Emprestimo> mostrarEmprestimo(@PathVariable Integer id){
        return emprestimoService.findEmprestimoById(id);
    }

    @PostMapping(value = "/adicionaremprestimo")
    public void adicionarEmprestimo(@RequestBody Emprestimo emprestimo){
        emprestimoService.saveEmprestimo(emprestimo);
    }

    @PutMapping(value = "/atualizaremprestimo/{id}")
    public void atualizarEmprestimo(@PathVariable Integer id, @RequestBody Emprestimo emprestimo){
        emprestimoService.updateEmprestimo(id, emprestimo);
    }

    @DeleteMapping(value = "/deletaremprestimo/{id}")
    public void removerEmprestimo(@PathVariable Integer id){
        emprestimoService.deleteEmprestimo(id);
    }
}
