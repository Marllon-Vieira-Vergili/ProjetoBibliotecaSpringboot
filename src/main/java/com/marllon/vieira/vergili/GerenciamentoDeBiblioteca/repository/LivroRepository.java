package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

    //Métodos que serão instanciados nos nossos Services Implementações, das lógicas;
}
