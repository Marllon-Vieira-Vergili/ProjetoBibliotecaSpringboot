package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro,Integer> {
    //Lógica dos métodos de CRUD para o services



    //Método para encontrar o livro pelo Titulo
    @Query("SELECT l FROM Livro l WHERE l.titulo = :titulo")
    List<Livro> findByTitulo(@Param(value= "titulo") String titulo); //e se eu tiver mais de um livro com o mesmo nome? vai virar lista...



}
