package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.implementationRepositories;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.repositoryInterfaces.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.repositoryInterfaces.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class AutorRepositoryImplement implements Serializable {


    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;


    AutorRepositoryImplement(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }


    @Transactional
    public void saveAutor(Autor autor) {
        autorRepository.save(autor);
    }

    public Optional<Autor> findAutorById(int id) {
        return autorRepository.findById(id);
    }

    public List<Autor> findAllAutor(){
        return autorRepository.findAll();
    }


    @Transactional
    public void deleteAutor(int id) {

        Autor autor = autorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Autor não encontrado!"));

        //Verificar se o autor tem livros associados

        if (autor.getLivros() != null &&  !autor.getLivros().isEmpty()){
            System.out.println("O autor possui livros associados. Excluindo os livros..");

            //excluindo os livros associados
            autor.getLivros().forEach(livro -> {
                livro.setAutor(null);
                livroRepository.delete(livro);
            });

            System.out.println("Livros associados foram excluídos!");
        }
        autorRepository.deleteById(id);
        System.out.println("Autor excluído com sucesso!");

    }


    @Transactional
    public void updateAutor(Autor autor) {
        autorRepository.save(autor);
    }

}
