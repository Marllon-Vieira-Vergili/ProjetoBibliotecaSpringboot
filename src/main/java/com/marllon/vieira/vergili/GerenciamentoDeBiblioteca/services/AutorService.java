package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.repositoryInterfaces.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.repositoryInterfaces.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

@Service
public class AutorService implements Serializable {

    Scanner sc = new Scanner(System.in);
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;


    public AutorService(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }




    public void saveAutor(Autor autor) {
        autorRepository.save(autor);
    }


    public Optional<Autor> findAutorById(int id) {

        if (id == 0 || id < 0) {
            System.out.println("Id inválida");
        }

        Optional <Autor> autor = autorRepository.findById(id);

        if (autorRepository.findById(id).isEmpty()){
            throw new NoSuchElementException("Autor não encontrado!");
        }
        System.out.println("ID encontrada! a id informada foi: " + id);

        System.out.println("Detalhes do autor: " + autor.get());
        return autorRepository.findById(id);

    }


    public List<Autor> findAllAutor(){

        if (autorRepository.findAll().isEmpty()){
            throw new NoSuchElementException("Não há nenhum elemento nesta lista de autor!");
        }
        return autorRepository.findAll();

    }

    public void updateAutor(int id, Autor autor){

        Optional<Autor> theAutor = autorRepository.findById(id);
        if (theAutor.isPresent()){
            autor.setId(id);
            autorRepository.save(autor);
        }

    }

    public void deleteAutor(int id){


        if (autorRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("Id não foi encontrada!");
        }

        autorRepository.deleteById(id);

    }

}
