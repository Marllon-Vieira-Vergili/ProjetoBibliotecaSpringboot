package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restControllers;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.LeitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/leitores")
public class LeitorController {

    @Autowired
    private LeitorService leitorService;

    public LeitorController(LeitorService leitorService) {
        this.leitorService = leitorService;
    }



    @GetMapping(value = "/mostrartodosleitores")
    public List<Leitor> mostrarTodosLeitores(){
        return leitorService.findAllLeitor();
    }


    @GetMapping(value = "/mostrarleitor/{id}")
    public Optional<Leitor> mostrarLeitor(@PathVariable Integer id){
        return leitorService.findLeitorById(id);
    }

    @PostMapping(value = "/adicionarleitor")
    public void adicionarLeitor(@RequestBody Leitor leitor){
        leitorService.saveLeitor(leitor);
    }

    @PutMapping(value = "/atualizarleitor/{id}")
    public void atualizarLeitor(@PathVariable Integer id, @RequestBody Leitor leitor){
        leitorService.updateLeitor(id, leitor);
    }

    @DeleteMapping(value = "/deletarleitor/{id}")
    public void removerLeitor(@PathVariable Integer id){
       leitorService.deleteLeitor(id);
    }
}
