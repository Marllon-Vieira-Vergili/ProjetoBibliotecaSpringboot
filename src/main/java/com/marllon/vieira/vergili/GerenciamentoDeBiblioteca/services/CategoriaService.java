package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.*;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.CategoriaRepositoryImplement;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.LivroRepositoryImplementation;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.CategoriaRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.LivroRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;



/*
SERVICES SÃO A LÒGICA DE NEGÓCIO, A LÒGICA DE NEGÓCIO É O QUE NOS DÁ O VALOR AOS DADOS, E
IMPLEMENTAÇÂO DAS LÒGICAS CORRETAS E TRATADAS EM TODOS OS MÈTODOS

 */


@Service
public class CategoriaService implements Serializable {


    //Instanciando apenas o Repositório de métodos para a Categoria, e Injetando

    @Autowired
    private LivroRepositoryImplementation livroRepositoryImplementation;

    @Autowired
    private CategoriaRepositoryImplement categoriaRepositoryImplement;

    @PersistenceContext
    private EntityManager entityManager;

    public CategoriaService(CategoriaRepositoryImplement categoriaRepositoryImplement){
        this.categoriaRepositoryImplement = categoriaRepositoryImplement;
    }



    public void saveCategoria(Categoria categoria) {

        //Verificar se os dados digitados não estão vazios
        if (categoria.getNomeCategoria() == null || categoria.getNomeCategoria().isEmpty() || categoria.getNomeCategoria().isBlank()) {
            throw new IllegalArgumentException("O nome da categoria não pode ficar em branco ou vazio!");
        }
        //Verificar se a categoria que será salvo já não existe no banco
        Optional<Categoria> categoriaExistente = categoriaRepositoryImplement.findCategoriaByNome(categoria.getNomeCategoria());
        if (categoriaExistente.isPresent()) {
            throw new IllegalArgumentException("Não é possível adicionar essa categoria, pois ele ja está no banco de dados!");
        }


        //Se a categoria não for informado, será criado uma nova categoria
        if (categoria.getNomeCategoria() == null) {
            throw new NullPointerException("A categoria não pode ser nula!");
        }


        //Verificar se a categoria existe no banco de dados
        Optional<Categoria> categoriaExistenteBanco = categoriaRepositoryImplement.findCategoriaById(categoria.getId());
        /*
        if (categoriaExistenteBanco.isEmpty()) {
            throw new IllegalArgumentException("A categoria informada não existe no banco de dados!");
        }

         */

        //associar a categoria existente ao livro
        categoria.setLivrosCategoria(categoriaExistenteBanco.get().getLivrosCategoria());
        categoriaRepositoryImplement.saveCategoria(categoria);

             /*
        {
    "nome": "livro2",
    "anoLancamento": 1998,
    "autor":{
        "id": 2,
        "nome":"autor2"
        Exemplo de associação de adição de um livro associado a um autor
    }
}
         */
    }





    public Optional<Categoria> findCategoriaById(int id) {

        Categoria novaCategoria = categoriaRepositoryImplement.findCategoriaById(id).orElseThrow(() -> new NoSuchElementException("Não há nenhum livro com essa id!"));
        return categoriaRepositoryImplement.findCategoriaById(id);
    }

    public Optional<Categoria> findCategoriaByNome(String nome){

        TypedQuery<Categoria> query = entityManager.createQuery("SELECT c FROM Categoria c WHERE c.nome =:nomeCategoria", Categoria.class);
        query.getSingleResult();
        query.setParameter("nomeCategoria", nome);
        return query.getResultList().stream().findFirst();
    }


    public List<Categoria> findAllCategorias(){

        try{
            return categoriaRepositoryImplement.findAllCategoria();
        }catch (NoSuchElementException e){
            return null;
        }
    }





    public void updateCategoria(int id, Categoria categoria){

        //Procurar a categoria já existente no banco de dados, por sua id
        Optional<Categoria> theCategoria = categoriaRepositoryImplement.findCategoriaById(id);


        //Verificar se a categoria está presente vamos atualizá-lo, sem mexer no livro
        if(theCategoria.isPresent()){
            Categoria existingCategoria = theCategoria.get(); //instanciando uma categoria existente, e obtendo os valores da categoria encontrada
            existingCategoria.setNomeCategoria(categoria.getNomeCategoria()); //setar o nome da categoria existente, pelo nome da categoria que passamos de parâmetro
            existingCategoria.setLivrosCategoria(categoria.getLivrosCategoria());
           categoriaRepositoryImplement.saveCategoria(existingCategoria);
        }else{
            throw new NoSuchElementException("Categoria não encontrado!");
        }

    }



    public void deleteCategoria(int id){

        //tentar encontrar a ID, senão achar,já encaminhar a exceção
        Categoria categoria = categoriaRepositoryImplement.findCategoriaById(id).orElseThrow(()-> new RuntimeException("Livro não encontrado com essa id!"));

        //Se a categoria for encontrado, iremos removê-lo, e desassociá-lo do livro
        if(categoria.getNomeCategoria() != null){
            categoria.getLivrosCategoria().remove(categoria);
        }
        categoria.getLivrosCategoria().clear();

        categoriaRepositoryImplement.deleteCategoria(categoria.getId());
        System.out.println("Categoria com a id: " + id + " deletado com sucesso!");
    }
}



