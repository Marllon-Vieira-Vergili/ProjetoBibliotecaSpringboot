package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.controllers;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.EmprestimoRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.EmprestimoResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/emprestimo")
public class EmprestimoController {


    @Autowired
    private EmprestimoService emprestimoService;


    @GetMapping(value = "/mostrarTodosEmprestimos")
    public Iterable<EmprestimoResponseDTO> mostrarTodosEmprestimos(){
        return emprestimoService.listarTodosOsEmprestimos();
    }

    @GetMapping(value = "/mostrarEmprestimoPelaId/{id}")
    public EmprestimoResponseDTO mostrarEmprestimoPelaId(@PathVariable Integer id){
        return emprestimoService.encontrarEmprestimoPorId(id);
    }

    @PostMapping(value = "/adicionarEmprestimo")
    public EmprestimoResponseDTO adicionarEmprestimo(@RequestBody EmprestimoRequestDTO emprestimoRequestDTO){
        return emprestimoService.salvarEmprestimo(emprestimoRequestDTO);
    }

    @PutMapping(value = "/atualizarEmprestimo/{id}")
    public EmprestimoResponseDTO atualizarEmprestimo(@PathVariable Integer id, @RequestBody
    EmprestimoRequestDTO emprestimoRequestDTO){
        return emprestimoService.atualizarEmprestimo(id,emprestimoRequestDTO);
    }

    @DeleteMapping(value = "/removerEmprestimo/{id}")
    public void deletarEmprestimo(@PathVariable Integer id){
        emprestimoService.removerEmprestimo(id);
    }

}
