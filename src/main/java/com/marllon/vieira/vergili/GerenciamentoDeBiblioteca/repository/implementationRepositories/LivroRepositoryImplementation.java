package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.implementationRepositories;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.repositoryInterfaces.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class LivroRepositoryImplementation  implements Serializable {


    private LivroRepository livroRepository;

    @Autowired
    public LivroRepositoryImplementation(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public void saveLivro(Livro livro) {

        livroRepository.save(livro);
    }

    public Optional<Livro> findLivroById(int id) {
        return livroRepository.findById(id);
    }

    public List<Livro> findAllLivro(){
        return livroRepository.findAll();
    }

    public void deleteLivro(int id) {
        livroRepository.deleteById(id);
    }

}
