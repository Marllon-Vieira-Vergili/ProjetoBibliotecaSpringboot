package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
    //Métodos que serão instanciados nos nossos Services Implementações, das lógicas;
}
