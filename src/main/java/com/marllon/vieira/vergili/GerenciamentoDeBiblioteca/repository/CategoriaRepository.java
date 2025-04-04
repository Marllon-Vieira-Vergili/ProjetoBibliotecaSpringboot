package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    //Lógica dos métodos de CRUD para o services

    //Método para encontrar a categoria pelo nome
    @Query("SELECT c FROM Categoria c WHERE c.nomeCategoria= :nome")
    Categoria findByNome(@Param(value = "nome") String nome);
}
