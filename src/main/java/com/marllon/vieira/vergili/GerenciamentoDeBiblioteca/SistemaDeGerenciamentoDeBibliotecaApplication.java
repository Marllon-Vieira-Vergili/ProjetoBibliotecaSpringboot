package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.legacyrepository.RepositoryDAO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.repositoryInterfaces.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.repositoryInterfaces.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SistemaDeGerenciamentoDeBibliotecaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaDeGerenciamentoDeBibliotecaApplication.class, args);
    }


//USANDO O JPA REPOSITORY INTERFACE
/*
    @Bean
    public CommandLineRunner runner(AutorRepository autorRepository) {

        return runner -> {

            //adicionarAutor(autorRepository);
            adicionarLivros();

        };
    }



    private void adicionarLivros(AutorRepository autorRepository) {

        Livro tempLivro = new Livro("O livro do teste", 2024);
        Autor tempAutor = new Autor("carlao"," carlaorios@guaxupe.com", "18563-758", "Guaxutinga");

        tempLivro.setAutor(tempAutor);
        autorRepository.save(tempAutor);
        System.out.print("Done!");
    }

    private void adicionarAutor(AutorRepository autorRepository) {

        Autor tempAutor = new Autor("George", "george@gmail.com", "35874-758", "Guaxupe");
        autorRepository.save(tempAutor);

        System.out.print("Done!");

    }

 */
}










/*
	@Bean
	public CommandLineRunner runner(RepositoryDAO repositoryDAO) {

		return runner -> {
            //encontrarLivroPelaId(repositoryDAO);
            //salvarUmLivro(repositoryDAO);
            //encontrarTodosOsAutores(repositoryDAO);
                //encontrarAutorPorId(repositoryDAO);
			//adicionarAutor(repositoryDAO);
            //encontrarTodosOsLivros(repositoryDAO);
            //atualizarAutor(repositoryDAO);
            //atualizarLivro(repositoryDAO);
            //deletarAutor(repositoryDAO);
            //deletarUmLivro(repositoryDAO);
		};
	}

    private void deletarUmLivro(RepositoryDAO repositoryDAO) {


        int theId = 9;

        Livro tempLivro = repositoryDAO.findLivroById(theId);
        repositoryDAO.deleteLivro(theId);

        System.out.println("Apagado! " + tempLivro);


    }

    private void deletarAutor(RepositoryDAO repositoryDAO) {

        int theId = 3;
        Autor tempAutor = repositoryDAO.findAutorById(theId);



        repositoryDAO.deleteAutor(theId);
        System.out.println("Autor deletado: " + tempAutor);
        System.out.println("Livros associados ao autor deletado" + tempAutor.getLivros());
        System.out.println("Done!");

    }

    private void atualizarLivro(RepositoryDAO repositoryDAO) {

        int theId = 1;

        Livro tempLivro = repositoryDAO.findLivroById(theId);
        tempLivro.setNome("O livro do teste");
        tempLivro.setAnoLancamento(2024);

        repositoryDAO.updateLivro(tempLivro);

        System.out.println("Done!");

    }

    private void atualizarAutor(RepositoryDAO repositoryDAO) {

        int theId = 8;
        Autor tempAutor = repositoryDAO.findAutorById(theId);
        tempAutor.setNome("carlao");
        tempAutor.setCidade("Corumbaiba");
        tempAutor.setEmail("carlaodecorumbaiba@outlook.com");
        tempAutor.setTelefone("3454-23245");

        repositoryDAO.updateAutor(tempAutor);
        System.out.println("Done!");
    }

    private void encontrarTodosOsLivros(RepositoryDAO repositoryDAO) {

        Iterable<Livro> livros = repositoryDAO.findAllLivro();
        System.out.println("Livros encontrados: " + livros);
    }

    private void encontrarLivroPelaId(RepositoryDAO repositoryDAO) {

        int theId = 1;
        Livro tempLivro = repositoryDAO.findLivroById(theId);
        System.out.println("Livro encontrado: " + tempLivro);

    }

    private void salvarUmLivro(RepositoryDAO repositoryDAO) {

        //Adicionar um livro
        Livro newlivro = new Livro("o wave doidão", 2006);

        //associar o livro a um autor

        Autor tempAutor = new Autor("wave ", "wavee@outlook.com", "545884-285", "Rio Pardo");

        newlivro.setAutor(tempAutor);

        //Salvar o livro no banco de dados
        repositoryDAO.saveLivro(newlivro);

        //imprimir que deu certo
        System.out.print("Done!");

        }

    private void encontrarTodosOsAutores(RepositoryDAO repositoryDAO) {

        //Encontra todos os autores
        Iterable<Autor> autores = repositoryDAO.findAllAutor();
        System.out.println(autores);
    }

    private void encontrarAutorPorId(RepositoryDAO repositoryDAO) {

        int theId = 1;
        Autor tempAutor = repositoryDAO.findAutorById(theId);
        System.out.println("Autor encontrado: " + tempAutor);
    }

    private void adicionarAutor(RepositoryDAO repositoryDAO) {


        //Adicionar os autores
        Autor tempAutor = new Autor("Joao", "joao@hotmail.com", "358975-458", "Muzambinho");


        //Salvar o autor no banco de dados
        repositoryDAO.saveAutor(tempAutor);
        System.out.print("Done!");

    }

 */