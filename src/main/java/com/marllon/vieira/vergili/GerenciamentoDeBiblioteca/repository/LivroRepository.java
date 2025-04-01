package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Integer> {



    //Métodos que serão instanciados nos nossos Services Implementações, das lógicas;



    //Encontrar todos os livros de uma determinada categoria


/*
    @Query("SELECT l FROM Livro l WHERE l.categoria_id = :id")
    List<Livro> findByCategoria(@Param("id") Integer id);


 */

}
