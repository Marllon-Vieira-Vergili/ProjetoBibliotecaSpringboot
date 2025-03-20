package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.LivroRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


/*
REPOSITÒRIO SOMENTE PARA INSTANCIAR E  REALIZAR A LÒGICA CRUA DOS CRUDS (CREATE, READ, UPDATE, DELETE)
LÒGICAS MAIS COMPLEXAS E TRATADAS DEVEM SER REALIZADAS NO SERVICE
 */


@Repository
public class LivroRepositoryImplementation  implements Serializable {


    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EntityManager entityManager;


    public LivroRepositoryImplementation(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }


    public void saveLivro(Livro livro) {

        livroRepository.save(livro);
    }

    public Optional<Livro> findLivroById(int id) {
        return livroRepository.findById(id);
    }

    public List<Livro> findAllLivro() {
        return livroRepository.findAll();
    }


    public void deleteLivro(int id) {
        livroRepository.deleteById(id);
    }


    public Optional<Livro> findByNome(String nome) {

        TypedQuery<Livro> query = entityManager.createQuery("SELECT l FROM Livro l WHERE l.nome =:nome", Livro.class);
        query.getSingleResult();
        query.setParameter("nome", nome);
        return query.getResultList().stream().findFirst();
        //return livroRepository.findByNome(nome);
    }
}


