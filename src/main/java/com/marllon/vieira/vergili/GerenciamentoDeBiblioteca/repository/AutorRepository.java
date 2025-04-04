package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Integer> {


    //Lógica dos métodos de CRUD para o services

    //Método para encontrar o autor pelo nome
    @Query("SELECT a FROM Autor a WHERE a.nome= :nome")
    List<Autor> findByNome(@Param(value = "nome") String nomeAutor); //e se eu tiver mais de um autor com o mesmo nome? vai virar lista...
}
