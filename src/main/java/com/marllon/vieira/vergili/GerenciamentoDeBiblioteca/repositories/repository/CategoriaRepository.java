package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface CategoriaRepository extends JpaRepository <Categoria, Integer> {

    //REPOSITORIO PARA INSTANCIAR E  REALIZAR A LÒGICA CRUA DOS CRUDS (CREATE, READ, UPDATE, DELETE)
    //NOS MÈTODOS DO PACOTE IMPLEMENTATION REPOSITORIES


   //Método Customizado de categoria, para encntrar pelo nome

    @Query("SELECT c FROM Categoria c WHERE c.nomeCategoria = :nomeCategoria")
      Optional<Categoria> findCategoriaByNome(@Param("nomeCategoria") String nome);
}
