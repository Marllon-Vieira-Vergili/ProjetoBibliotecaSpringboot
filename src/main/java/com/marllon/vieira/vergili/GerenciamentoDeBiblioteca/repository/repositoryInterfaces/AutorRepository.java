package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.repositoryInterfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AutorRepository extends JpaRepository <Autor, Integer> {


}
