package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.LivroRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivroRepository extends JpaRepository<Livro,Integer> {
    //Lógica dos métodos de CRUD para o services



    //Método para encontrar o livro pelo Titulo
    @Query("SELECT l FROM Livro l WHERE l.titulo = :titulo")
    Livro findByTitulo(@Param("titulo") String livroRequest);

}
