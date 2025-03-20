package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.EmprestimoRepositoryImplementation;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;


/*
SERVICES SÃO A LÒGICA DE NEGÓCIO, A LÒGICA DE NEGÓCIO É O QUE NOS DÁ O VALOR AOS DADOS, E
IMPLEMENTAÇÂO DAS LÒGICAS CORRETAS E TRATADAS EM TODOS OS MÈTODOS

 */


@Service
public class EmprestimoService implements Serializable {

    Scanner sc = new Scanner(System.in);


    //Instanciando apenas o Repositório de métodos para o Emprestimo, e Injetando
    @Autowired
    private EmprestimoRepositoryImplementation emprestimoRepositoryImplementation;


    public EmprestimoService(EmprestimoRepositoryImplementation emprestimoRepositoryImplementation) {
        this.emprestimoRepositoryImplementation = emprestimoRepositoryImplementation;
    }

    public void saveEmprestimo(Emprestimo emprestimo) {
        emprestimoRepositoryImplementation.saveEmprestimo(emprestimo);
    }


    public Optional<Emprestimo> findEmprestimoById(int id) {

        if (id == 0 || id < 0) {
            System.out.println("Id inválida");
        }

        Optional <Emprestimo> emprestimo = emprestimoRepositoryImplementation.findEmprestimoById(id);

        if (emprestimoRepositoryImplementation.findEmprestimoById(id).isEmpty()){
            throw new NoSuchElementException("Emprestimo não encontrado!");
        }
        System.out.println("ID encontrada! a id informada foi: " + id);

        System.out.println("Detalhes do emprestimo: " + emprestimo.get());
        return emprestimoRepositoryImplementation.findEmprestimoById(id);

    }


    public List<Emprestimo> findAllEmprestimo(){

        if (emprestimoRepositoryImplementation.findAllEmprestimo().isEmpty()){
            throw new NoSuchElementException("Não há nenhum elemento nesta lista de emprestimo!");
        }
        return emprestimoRepositoryImplementation.findAllEmprestimo();

    }

    public void updateEmprestimo(int id, Emprestimo emprestimo){

        Optional<Emprestimo> theEmprestimo = emprestimoRepositoryImplementation.findEmprestimoById(id);
        if (theEmprestimo.isPresent()){
            emprestimo.setId(id);
            emprestimoRepositoryImplementation.saveEmprestimo(emprestimo);
        }

    }

    public void deleteEmprestimo(int id){


        if (emprestimoRepositoryImplementation.findEmprestimoById(id).isEmpty()){
            throw new IllegalArgumentException("Id não foi encontrada!");
        }

        emprestimoRepositoryImplementation.deleteEmprestimo(id);

    }

}
