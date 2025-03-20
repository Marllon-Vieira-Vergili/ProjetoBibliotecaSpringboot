package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.LeitorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
public class LeitorRepositoryImplementation implements Serializable {


    private LeitorRepository leitorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public LeitorRepositoryImplementation(LeitorRepository leitorRepository){
        this.leitorRepository = leitorRepository;
    }

    public void saveLeitor(Leitor leitor) {
        leitorRepository.save(leitor);
    }

    public Optional<Leitor> findLeitorById(int id) {
        return leitorRepository.findById(id);
    }

    public List<Leitor> findAllLeitor(){
        return leitorRepository.findAll();
    }

    public void deleteLeitor(int id) {
        leitorRepository.deleteById(id);
    }

    public Optional<Leitor> encontrarLeitorPeloNome(String nome) {

        TypedQuery<Leitor> query = entityManager.createQuery("SELECT l FROM Leitor l WHERE l.nome = :nome", Leitor.class);
        query.getSingleResult();
        query.setParameter("nome", nome);
        return query.getResultList().stream().findFirst(); //é usado para pegar o primeiro resultado da lista (se existir).
    }

    public Optional<Leitor> findLeitorByEmail(String email){

        TypedQuery<Leitor> query = entityManager.createQuery("SELECT l FROM Leitor l WHERE l.email = :email", Leitor.class);
        query.getSingleResult();
        query.setParameter("email", email);
        return query.getResultList().stream().findFirst();

    }
}

