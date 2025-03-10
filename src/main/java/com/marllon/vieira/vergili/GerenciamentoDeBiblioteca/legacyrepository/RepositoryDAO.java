
package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.legacyrepository;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;

import java.util.List;

public interface RepositoryDAO {

    //Métodos Salvar
    void saveAutor(Autor autor); //save for autor
    void saveLivro(Livro livro); //save for livro


    //Métodos Buscar

    Autor findAutorById(int id); //find autor by id
    List<Autor> findAllAutor();
    Livro findLivroById(int id);//find all autor
    List<Livro> findAllLivro();

    //Métodos Atualizar

    void updateAutor(Autor autor);
    void updateLivro(Livro livro);

    //Métodos Deletar

    void deleteAutor(int id);
    void deleteLivro(int id);
}
