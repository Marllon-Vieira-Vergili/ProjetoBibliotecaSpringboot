package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


/*
REPOSITÒRIO SOMENTE PARA INSTANCIAR E  REALIZAR A LÒGICA CRUA DOS CRUDS (CREATE, READ, UPDATE, DELETE)
LÒGICAS MAIS COMPLEXAS E TRATADAS DEVEM SER REALIZADAS NO SERVICE
 */




@Repository
public class EmprestimoRepositoryImplementation implements Serializable {


    private final EmprestimoRepository emprestimoRepository;

    @Autowired
    public EmprestimoRepositoryImplementation(EmprestimoRepository emprestimoRepository){
        this.emprestimoRepository = emprestimoRepository;
    }

    public void saveEmprestimo(Emprestimo emprestimo) {
        emprestimoRepository.save(emprestimo);
    }

    public Optional<Emprestimo> findEmprestimoById(int id) {
        return emprestimoRepository.findById(id);
    }

    public List<Emprestimo> findAllEmprestimo(){
        return emprestimoRepository.findAll();
    }

    public void deleteEmprestimo(int id) {
        emprestimoRepository.deleteById(id);
    }

}
