package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restController;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation.AutorRequestComLivro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LeitorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.AutorComLivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.AutorAssociationLivroService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.LeitorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/leitor")
@Tag(name = "Leitor", description = "Endpoints para gerenciamento de Leitores")
public class LeitorController {

    @Autowired
    private LeitorService leitorService;


    //Método só para testar se está adicionando  certinho, comentado...
    @PostMapping("/adicionarLeitor")
    public Leitor adicionarLeitor(@RequestBody LeitorRequest leitorRequest){
        return leitorService.criarLeitor(leitorRequest);
    }

    @PutMapping("/atualizar/{id}")
    public void atualiza(@PathVariable Integer id, @RequestBody LeitorRequest leitorRequest){
        leitorService.atualizarLeitor(id, leitorRequest);
    }


    @GetMapping("/encontrarid/{id}")
    public Leitor encontraelaid(@PathVariable Integer id){
        return leitorService.encontrarLeitorPorId(id);
    }

    @GetMapping("/todos")
    public List<Leitor> leitorestodos(){
        return leitorService.encontrarTodosLeitores();
    }

    @GetMapping("/peloNome{nome}")
    public List<Leitor> encontrarNome(@PathVariable String nome){
        return leitorService.encontrarLeitorPorNome(nome);
    }

    @DeleteMapping("/deleta/{id}")
    public void deleta(@PathVariable Integer id){
        leitorService.deletarLeitor(id);
    }


}
