package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.controllers.associations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.EmprestimoELeitoresRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LeitorComEmprestimoRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.EmprestimoELeitoresResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LeitorComEmprestimoResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces.Emprestimo_Leitores;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "marllon")
@RequestMapping(value = "/api/emprestimo-leitor")
public class EmprestimoAndLeitor {


    @Autowired
    private Emprestimo_Leitores emprestimoLeitores;


    @PostMapping(value = "/associarEmprestimoAleitor")
    public EmprestimoELeitoresResponseDTO associarEmprestimoALeitor(@RequestBody EmprestimoELeitoresRequestDTO
                                                                                  emprestimoELeitoresRequestDTO){
        return emprestimoLeitores.associarEmprestimoALeitor(emprestimoELeitoresRequestDTO);
    }

    @PostMapping(value = "/associarLeitorAoEmprestimo")
    public LeitorComEmprestimoResponseDTO associarLeitorAoEmprestimo(@RequestBody LeitorComEmprestimoRequestDTO
                                                                            leitorComEmprestimoRequestDTO){
        return emprestimoLeitores.associarLeitorAEmprestimo(leitorComEmprestimoRequestDTO);
    }

    @GetMapping(value = "/encontrarEmprestimoDeLeitor/{id}")
    public EmprestimoELeitoresResponseDTO encontrarEmprestimosLeitor(@PathVariable Integer id){
        return emprestimoLeitores.encontrarEmprestimoDeLeitor(id);
    }
}
