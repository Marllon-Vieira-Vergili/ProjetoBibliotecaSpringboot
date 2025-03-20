package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.testing;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.LivroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LivroTesting {


    @Bean
    public  CommandLineRunner commandLineRunner(LivroService livroService){
        return runner ->{

            //deletarLivroPelaId(livroService);
            //adicionarLivro(livroService);
            //encontrarTodosLivros(livroService);
            //encontrarLivroPeloTitulo(livroService);
            //encontrarLivroPelaId(livroService);
            //atualizarLivro(livroService);
        };

    }

    /*
    private void encontrarLivroPeloTitulo(LivroService livroService) {
        livroService.encontrarLivroPeloTitulo("O livro teste");
    }

     */

    private void deletarLivroPelaId(LivroService livroService) {

    }

    private void atualizarLivro(LivroService livroService){

    }

    private void encontrarLivroPelaId(LivroService livroService) {

    }

    private void encontrarTodosLivros(LivroService livroService) {



    }


    private void adicionarLivro(LivroService livroService) {


    }
}

