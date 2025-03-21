package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.restControllers;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.dto.AutorDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.AutorService;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }



//Método funcionando
    @Operation(description = "Mostra todos os autores em um JSON")
    //@ApiResponses(value = (@ApiResponse(responseCode = "200", description = "Retorna todos os autores"))
    @GetMapping(value = "/mostrartodosautores")
    public List<AutorDTO> mostrarTodosAutores(){

        //Encontrar todos os autores
        return autorService.findAllAutor();
    }



    @GetMapping(value = "/mostrarautor/{id}")
    public ResponseEntity<AutorDTO> mostrarAutorPelaID(@PathVariable Integer id){
        AutorDTO autorDTO = autorService.findAutorById(id);
        if(autorDTO == null){
            return ResponseEntity.notFound().build(); //Retornar 404 caso não encontrar
        }
        return ResponseEntity.ok(autorDTO); //Retorna 200 com o autor encontrado
    }

    @GetMapping(value = "/mostrarautorporemail/{email}")
    public Optional<AutorDTO> mostrarAutorPorEmail(@PathVariable String email){
        return autorService.findAutorByEmail(email);
    }


    //Funcionou
    @PostMapping(value = "/adicionarautor")
    public ResponseEntity<AutorDTO> adicionarAutor(@RequestBody AutorDTO autor){
        autorService.saveAutor(autor);
        if(autor == null){
            return ResponseEntity.ofNullable(null);
        }
        return ResponseEntity.ok().build();
    }


    //funcionou
    @PutMapping(value = "/atualizarautor/{id}")
    public ResponseEntity<AutorDTO> atualizarAutor(@PathVariable Integer id, @RequestBody AutorDTO autor){

        if (autor == null){
            return ResponseEntity.badRequest().build();
        }
        AutorDTO autoratualizado = autorService.updateAutor(id, autor);
        return ResponseEntity.ok(autoratualizado);
    }



//Funcionou
    @DeleteMapping(value = "/deletarautor/{id}")
    public void removerAutor(@PathVariable Integer id){
        autorService.deleteAutor(id);
    }
}
