package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmprestimoRepository extends JpaRepository <Emprestimo, Integer> {

    //REPOSITORIO PARA INSTANCIAR E  REALIZAR A LÒGICA CRUA DOS CRUDS (CREATE, READ, UPDATE, DELETE)
    //NOS MÈTODOS DO PACOTE IMPLEMENTATION REPOSITORIES

}
