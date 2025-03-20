package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.AutorRepository;
import jakarta.persistence.EntityManager;
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
public class AutorRepositoryImplement implements Serializable {


    @Autowired
    AutorRepository autorRepository;


    public AutorRepositoryImplement(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }


    @Transactional
    public void saveAutor(Autor autor) {
        autorRepository.save(autor);
    }

    public Optional<Autor> findAutorById(int id) {
        return autorRepository.findById(id);
    }


    //Verificar se ele irá encontrar pelo email, é Implementação customizada!
    public Optional<Autor> findByEmail(String email) {// Criando a consulta JPQL
        return autorRepository.findByEmail(email);

    }

    public List<Autor> findAllAutor(){
        return autorRepository.findAll();
    }


    @Transactional
    public void deleteAutor(int id) {
        autorRepository.deleteById(id);
    }


    @Transactional
    public void updateAutor(Autor autor) {
        autorRepository.save(autor);
    }

}
