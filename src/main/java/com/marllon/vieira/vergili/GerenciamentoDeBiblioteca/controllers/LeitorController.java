package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.controllers;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.LeitorRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LeitorResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.LeitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/leitor")
public class LeitorController {

    @Autowired
    private LeitorService leitorService;


    @GetMapping(value = "/mostrarLeitorPorId/{id}") //{id} pela sua id que será passada no cabeçalho da página
    public LeitorResponseDTO mostrarLeitorPorId(@PathVariable Integer id){
        return leitorService.listarLeitorPorId(id);
    }

    @GetMapping(value = "/mostrarTodosOsLeitores")
    public Iterable<LeitorResponseDTO> mostrarTodosLeitores(){
        return leitorService.listarTodosOsLeitores();
    }

    @PostMapping(value = "/adicionarLeitor")
    public LeitorResponseDTO adicionarLeitor(@RequestBody LeitorRequestDTO requestDTO){ //passar como parametro o corpo de resposta da entidade
        return leitorService.salvarLeitor(requestDTO);
    }

    @PutMapping(value = "/atualizarLeitor/{id}")//{id} pela sua id que será passada no cabeçalho da página
    public LeitorResponseDTO atualizarLeitor(@RequestParam Integer id, @RequestBody LeitorRequestDTO leitorRequestDTO){
        return leitorService.atualizarLeitor(id, leitorRequestDTO);
    }

    @DeleteMapping(value = "/deletarLeitor/{id}")
    public void deletarAutorPelaId(@PathVariable Integer id){ //Caminho de uma variável pra ele pegar a id dessa entidade
        leitorService.removerLeitor(id);
    }
}
