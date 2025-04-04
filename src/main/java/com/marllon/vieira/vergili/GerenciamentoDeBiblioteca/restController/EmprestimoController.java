package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restController;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.EmprestimoRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/emprestimos")
public class EmprestimoController {


    @Autowired
    private EmprestimoService emprestimoService;


    @PostMapping(value = "/criarEmprestimo")
    public Emprestimo criarEmprestimo(@RequestBody EmprestimoRequest emprestimoRequest) {
        return emprestimoService.criarEmprestimo(emprestimoRequest);
    }

    @PutMapping(value = "/atualizarEmprestimo/{id}")
    public void atualizarEmprestimo(@PathVariable Integer id, @RequestBody EmprestimoRequest emprestimoRequest) {
        emprestimoService.atualizarEmprestimo(id, emprestimoRequest);
    }

    @DeleteMapping(value = "/deletarEmprestimo/{id}")
    public void deletarEmprestimo(@PathVariable Integer id){
        emprestimoService.deletarEmprestimo(id);
    }

    @GetMapping(value = "/mostrarEmprestimo/{id}")
    public Emprestimo mostrarEmprestimoPelaId(@PathVariable Integer id){
        return emprestimoService.encontrarEmprestimoPorId(id);
    }
    @GetMapping(value = "/mostrartodos")
    public List<Emprestimo> mostrartodos(){
        return emprestimoService.encontrarTodosEmprestimos();
    }

}
