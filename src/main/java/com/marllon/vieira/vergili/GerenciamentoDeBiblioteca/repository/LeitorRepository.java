package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeitorRepository extends JpaRepository<Leitor, Integer> {
    //Lógica dos métodos de CRUD para o services


    //Método para encontrar o leitor pelo nome
    @Query("SELECT l FROM Leitor l WHERE l.nome= :nome")
    List<Leitor> findByNome(@Param(value = "nome") String nome); //e se eu tiver mais de um leitor com o mesmo nome? vai virar lista...
}
