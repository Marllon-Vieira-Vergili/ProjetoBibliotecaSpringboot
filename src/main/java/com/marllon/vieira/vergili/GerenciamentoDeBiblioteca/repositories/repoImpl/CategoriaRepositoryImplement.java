package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.CategoriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
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
public class CategoriaRepositoryImplement implements Serializable {


    @Autowired
    CategoriaRepository categoriaRepository;

    @PersistenceContext
    EntityManager entityManager;

    public CategoriaRepositoryImplement(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    @Transactional
    public void saveCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public Optional<Categoria> findCategoriaById(int id) {
        return categoriaRepository.findById(id);
    }

    public List<Categoria> findAllCategoria(){
        return categoriaRepository.findAll();
    }


    @Transactional
    public void deleteCategoria(int id) {
        categoriaRepository.deleteById(id);
    }

    public Optional<Categoria> findCategoriaByNome(String nome) {

        TypedQuery<Categoria> query = entityManager.createQuery("SELECT c FROM Categoria c WHERE c.nomeCategoria = :nome", Categoria.class);
        //query.getSingleResult();
        query.setParameter("nome", nome); //sta linha está configurando o valor do parâmetro na consulta JPQL (Java Persistence Query Language).
        return query.getResultList().stream().findFirst(); //Retorna o primeiro resultado encontrado como um Optional. Caso nenhum resultado seja encontrado, o Optional estará vazio (representando Optional.empty()).

    }
}
