/*
package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.testing;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.AutorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutorTesting {



    @Bean
    public CommandLineRunner runner(AutorService autorService) {

        return runner -> {
            //encontrarTodosOsAutores(autorService); //TESTE OK(chamou apenas pelo endpoint, mas funcionou
            //encontrarAutorPorId(autorService); //TESTE OK(chamou apenas pelo endpoint, mas funcionou
            //adicionarAutor(autorService); //TESTE OK (chamou apenas pelo endpoint, mas funcionou)
            //atualizarAutor(autorService);// TESTE OK (chamou apenas pelo endpoint, mas funcionou)
            //deletarAutor(autorService); //TESTE OK

        };
    }


    private void deletarAutor(AutorService autorService) {

        autorService.deleteAutor(16);


    }


    private void atualizarAutor(AutorService autorService) {

        autorService.updateAutor(1, new Autor("marllon", "marllonvieira@gmail.com", "5456212", "marllon city"));

    }

    private void encontrarTodosOsAutores(AutorService autorService) {


        System.out.println("todos os autores encontrados" + autorService.findAllAutor());
    }

    private void encontrarAutorPorId(AutorService autorService) {

        System.out.println("Autor: " + autorService.findAutorById(16));

    }



    //Método instanciou corretamente pelo Endpoint POST
    public void adicionarAutor(AutorService autorService) {

       Autor autor = new Autor();
       autor.setNome("Josué");
       autor.setEmail("josue@email.com");
       autor.setTelefone("3551-7258");
       autor.setCidade("Goias");

       autorService.saveAutor(autor);

        //autorService.saveAutor(new Autor("josue", "josue@email.com", "3551-7258", "Goiás"));
        System.out.println("Autor salvo com sucesso!");

    }

}


 */