package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.legacyrepository;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import jakarta.persistence.EntityManager;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Repository
public class RepositoryDAOImplementation implements RepositoryDAO {




    private EntityManager entityManager;

    @Autowired
    public RepositoryDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void saveAutor(Autor autor) {
        entityManager.persist(autor);
    }

    @Override
    @Transactional
    public void saveLivro(Livro livro) {

        //condição de entrada
        if(livro.getNome() == null || livro.getNome().isEmpty()){
            throw new IllegalArgumentException("Nome do livro não pode ser nulo ou vazio");
        }

        entityManager.persist(livro);

    }


    @Override
    public Autor findAutorById(int id) {


        Autor tempAutor = entityManager.find(Autor.class, id);
        if (tempAutor == null) {
            throw new NoSuchElementException("Autor não encontrado");
        }
        return tempAutor;
    }

    @Override
    public List<Autor> findAllAutor() {
        List<Autor> query = entityManager.createQuery
                ("Select a From Autor a ", Autor.class).getResultList();

        return query;
    }

    @Override
    public Livro findLivroById(int id) {
        Livro livro = entityManager.find(Livro.class, id);
        if (livro == null) {
            throw new NoSuchElementException("Livro não encontrado");
        }
        return livro;
    }

    @Override
    public List<Livro> findAllLivro() {
        List<Livro> query = entityManager.createQuery(
                "Select l From Livro l", Livro.class).getResultList();

        return query;

    }

    @Override
    @Transactional
    public void updateAutor(Autor autor) {
        entityManager.merge(autor);
    }

    @Override
    @Transactional
    public void updateLivro(Livro livro) {

        entityManager.merge(livro);
    }

    @Override
    @Transactional
    public void deleteAutor(int id) {

        //Encontrar a ID do meu usuário
        Autor tempAutor = entityManager.find(Autor.class, id);

        if (tempAutor == null) {
            throw new NoSuchElementException("Autor não encontrado");
        }

        //Verificar se o autor tem livros associados
        if (tempAutor.getLivros().isEmpty()) {
            System.out.println("O autor não tem livros associados");
            entityManager.remove(tempAutor);

        } else {
            //Remover a assosiação do autor com o livro
            for (Livro tempLivro : tempAutor.getLivros()) {
                entityManager.remove(tempLivro);
            }
        }
        entityManager.remove(tempAutor);
    }

    @Override
    @Transactional
    public void deleteLivro(int id) {

        //Encontrar a ID do meu usuário
        Livro tempLivro = entityManager.find(Livro.class, id);
        entityManager.remove(tempLivro);
    }
}





