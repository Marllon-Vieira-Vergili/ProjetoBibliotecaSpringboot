

package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.testing.testingAssociation;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.LivroRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.AutorService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class AutorELivroTesting {


    @Autowired
    private AutorService autorService;
    @Autowired
    private LivroService livroService;



    /*

    Meta de amanha: buscar o autor e dentro do autor, retornar um array de livros
    e ao buscar em livro, retornar o autor dentro do livro
     */

    @Bean
    public CommandLineRunner commandLineRunner(AutorService autorService, LivroService livroService){
        return run ->{

            //associarNovoAutorENovoLivro(autorRepository, livroRepository); //Funcionando


            //deletarAutorELivro(autorRepository, livroRepository); //Funcionou

            //deletarLivroSemAutor(autorRepository, livroRepository);

            //encontrarAutorElivro(autorService, livroService);


            //atualizarAutorELivro(autorRepository, livroRepository);
        };
    }

    private void atualizarAutorELivro(AutorService autorService, LivroService livroService) {


    }


    private void encontrarAutorElivro(AutorService autorService, LivroService livroService ) {

    }

    private void deletarAutorELivro(AutorService autorService, LivroService livroService) {

    }




    private void associarNovoAutorENovoLivro(AutorService autorService, LivroService livroService) {




    }
}

