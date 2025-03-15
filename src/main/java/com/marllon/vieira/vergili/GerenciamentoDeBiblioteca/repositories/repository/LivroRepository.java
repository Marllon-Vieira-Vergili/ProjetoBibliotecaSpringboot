package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.repositoryInterfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

}
