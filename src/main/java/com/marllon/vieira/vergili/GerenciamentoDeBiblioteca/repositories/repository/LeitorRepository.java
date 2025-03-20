package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface LeitorRepository extends JpaRepository <Leitor, Integer> {


    //REPOSITORIO PARA INSTANCIAR E  REALIZAR A LÒGICA CRUA DOS CRUDS (CREATE, READ, UPDATE, DELETE)
    //NOS MÈTODOS DO PACOTE IMPLEMENTATION REPOSITORIES

    //MÈTODO CUSTOMIZADO DO LEITOR

    @Query("SELECT l FROM Leitor l WHERE l.nome = :nome")
    public Optional<Leitor> encontrarLeitorPeloNome(@Param("nome") String nome);

    @Query("SELECT l FROM Leitor l WHERE l.email = :email")
    public Optional<Leitor> findLeitorByEmail(@Param("email") String email);
}
