package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AutorRepository extends JpaRepository <Autor, Integer> {

    //REPOSITORIO PARA INSTANCIAR E  REALIZAR A LÒGICA CRUA DOS CRUDS (CREATE, READ, UPDATE, DELETE)
    //NOS MÈTODOS DO PACOTE IMPLEMENTATION REPOSITORIES



   Optional<Autor> findByEmail(String email); //Método customizado, encontrar um autor pelo email dele

}
