package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

    //REPOSITORIO PARA INSTANCIAR E  REALIZAR A LÒGICA CRUA DOS CRUDS (CREATE, READ, UPDATE, DELETE)
    //NOS MÈTODOS DO PACOTE IMPLEMENTATION REPOSITORIES


    //Método customizado de encontrar um livro pelo Título
    @Query("SELECT l FROM Livro l WHERE l.nome = :nome")
    Optional<Livro> findByNome(@Param("nome") String nome);
}
