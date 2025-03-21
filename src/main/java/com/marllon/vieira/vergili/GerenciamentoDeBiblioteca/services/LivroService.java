package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.*;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.AutorRepositoryImplement;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.CategoriaRepositoryImplement;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.LivroRepositoryImplementation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;



/*
SERVICES SÃO A LÒGICA DE NEGÓCIO, A LÒGICA DE NEGÓCIO É O QUE NOS DÁ O VALOR AOS DADOS, E
IMPLEMENTAÇÂO DAS LÒGICAS CORRETAS E TRATADAS EM TODOS OS MÈTODOS

 */


@Service
public class LivroService implements Serializable {


    //Instanciando apenas o Repositório de métodos para o Livro, e Injetando
    @Autowired
    private LivroRepositoryImplementation livroRepositoryImplementation;

    @Autowired
    private AutorRepositoryImplement autorRepositoryImplement;

    @Autowired
    private CategoriaRepositoryImplement categoriaRepositoryImplement;

    @PersistenceContext
    private EntityManager entityManager;

    public LivroService(LivroRepositoryImplementation livroRepositoryImplementation) {
        this.livroRepositoryImplementation = livroRepositoryImplementation;
    }

    //ta dando erro no json?
    public void saveLivro(Livro livro) {
        //Verificar se os dados digitados não estão vazios
        if (livro.getNome() == null || livro.getNome().isEmpty() || livro.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do livro não pode ficar em branco ou vazio!");
        }
        //Verificar se o livro que será salvo já não existe no banco
        Optional<Livro> livroExistente = livroRepositoryImplementation.findByNome(livro.getNome());
        if (livroExistente.isPresent()) {
            throw new IllegalArgumentException("Não é possível adicionar esse livro, pois ele ja está no banco de dados!");
        }


        //Verificar se o autor existe no banco de dados
        Optional<Autor> autorExistente = autorRepositoryImplement.findAutorById(livro.getAutor().getId());
        if (autorExistente.isEmpty()) {
            throw new IllegalArgumentException("O autor informado não existe no banco de dados!");
        }
        livro.setAutor(autorExistente.get());

        //Associar o livro a uma categoria existente
        if (livro.getCategorias() != null) {
            List<Categoria> categoriasAssociadas = new ArrayList<>();
            for (Categoria c : livro.getCategorias()) {
                Categoria categoriaDoBancoDeDados = categoriaRepositoryImplement.findCategoriaByNome(c.getNomeCategoria())
                        .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada com esse nome: " + c.getNomeCategoria()));
                categoriasAssociadas.add(categoriaDoBancoDeDados);

            }
            livro.setCategorias(categoriasAssociadas);
        }

        //associar o livro a um leitor
        if(livro.getLeitor() != null){
            
        }

        livroRepositoryImplementation.saveLivro(livro);
             /*
             COmo meu JSON PRECISA SAIR
{
  "id": 1073741824,
  "nome": "string",
  "anoLancamento": 1073741824,
  "autor": {
    "id": 1073741824,
    "nome": "string",
    "email": "string",
    "telefone": "string",
    "cidade": "string",
    "livros": [
      "string"
    ]
  },
  "leitor": [
    {
      "id": 1073741824,
      "nome": "string",
      "sobrenome": "string",
      "email": "string",
      "idade": 1073741824,
      "livro": [
        "string"
      ],
      "leitorEmprestimos": [
        {
          "id": 1073741824,
          "dataEmprestimo": "2025-03-21T12:17:41.727Z",
          "dataDevolucao": "2025-03-21T12:17:41.727Z",
          "estaEmprestado": true,
          "livros": [
            "string"
          ],
          "emprestimoParaLeitores": [
            "string"
          ]
        }
      ]
    }
  ],
  "livroEmprestimos": [
    {
      "id": 1073741824,
      "dataEmprestimo": "2025-03-21T12:17:41.727Z",
      "dataDevolucao": "2025-03-21T12:17:41.727Z",
      "estaEmprestado": true,
      "livros": [
        "string"
      ],
      "emprestimoParaLeitores": [
        "string"
      ]
    }
  ],
  "categorias": [
    {
      "id": 1073741824,
      "nomeCategoria": "string",
      "livrosCategoria": [
        "string"
      ]
    }
  ]
}
         */
    }


    public Optional<Livro> findLivroById(int id) {

        Livro livro = livroRepositoryImplementation.findLivroById(id).orElseThrow(() -> new NoSuchElementException("Não há nenhum livro com esse id!"));

        return livroRepositoryImplementation.findLivroById(id);
    }


    public List<Livro> findAllivro() {

        if (livroRepositoryImplementation.findAllLivro().isEmpty()){
            throw new NoSuchElementException("Não há nenhum livro nesta lista!");
    }
    return livroRepositoryImplementation.findAllLivro();
    }


    public Optional<Livro> encontrarLivroPeloTitulo(String titulo){

        if (livroRepositoryImplementation.findByNome(titulo).isEmpty()){
            throw new NoSuchElementException("Não há nenhum livro com esse título!");
        }
            return livroRepositoryImplementation.findByNome(titulo);

        /*
        COmo meu json precisa sair
        {
  "id": 1073741824,
  "nome": "string",
  "anoLancamento": 1073741824,
  "autor": {
    "id": 1073741824,
    "nome": "string",
    "email": "string",
    "telefone": "string",
    "cidade": "string",
    "livros": [
      "string"
    ]
  },
  "leitor": [
    {
      "id": 1073741824,
      "nome": "string",
      "sobrenome": "string",
      "email": "string",
      "idade": 1073741824,
      "livro": [
        "string"
      ],
      "leitorEmprestimos": [
        {
          "id": 1073741824,
          "dataEmprestimo": "2025-03-21T12:18:30.313Z",
          "dataDevolucao": "2025-03-21T12:18:30.313Z",
          "estaEmprestado": true,
          "livros": [
            "string"
          ],
          "emprestimoParaLeitores": [
            "string"
          ]
        }
      ]
    }
  ],
  "livroEmprestimos": [
    {
      "id": 1073741824,
      "dataEmprestimo": "2025-03-21T12:18:30.313Z",
      "dataDevolucao": "2025-03-21T12:18:30.313Z",
      "estaEmprestado": true,
      "livros": [
        "string"
      ],
      "emprestimoParaLeitores": [
        "string"
      ]
    }
  ],
  "categorias": [
    {
      "id": 1073741824,
      "nomeCategoria": "string",
      "livrosCategoria": [
        "string"
      ]
    }
  ]
}
         */

    }


    public void updateLivro(int id, Livro livro){

        //Procurar o livro já existente no banco de dados, por sua id
        Optional<Livro> theLivro = livroRepositoryImplementation.findLivroById(id);


        //Verificar se o livro está presente vamos atualizá-lo, sem mexer no autor
        if(theLivro.isPresent()){
            Livro existingLivro = theLivro.get(); //instanciando um livroExistente, e obtendo os valores do livro encontrado
            existingLivro.setNome(livro.getNome()); //setar o nome do livro existente, pelo nome do livro que passamos de parâmetro
            existingLivro.setAnoLancamento(livro.getAnoLancamento());
            livroRepositoryImplementation.saveLivro(existingLivro);
        }else{
            throw new NoSuchElementException("livro não encontrado!");
        }


        /*Como meu JSON precisa sair:

        {
  "id": 1073741824,
  "nome": "string",
  "anoLancamento": 1073741824,
  "autor": {
    "id": 1073741824,
    "nome": "string",
    "email": "string",
    "telefone": "string",
    "cidade": "string",
    "livros": [
      "string"
    ]
  },
  "leitor": [
    {
      "id": 1073741824,
      "nome": "string",
      "sobrenome": "string",
      "email": "string",
      "idade": 1073741824,
      "livro": [
        "string"
      ],
      "leitorEmprestimos": [
        {
          "id": 1073741824,
          "dataEmprestimo": "2025-03-21T12:16:39.410Z",
          "dataDevolucao": "2025-03-21T12:16:39.410Z",
          "estaEmprestado": true,
          "livros": [
            "string"
          ],
          "emprestimoParaLeitores": [
            "string"
          ]
        }
      ]
    }
  ],
  "livroEmprestimos": [
    {
      "id": 1073741824,
      "dataEmprestimo": "2025-03-21T12:16:39.410Z",
      "dataDevolucao": "2025-03-21T12:16:39.410Z",
      "estaEmprestado": true,
      "livros": [
        "string"
      ],
      "emprestimoParaLeitores": [
        "string"
      ]
    }
  ],
  "categorias": [
    {
      "id": 1073741824,
      "nomeCategoria": "string",
      "livrosCategoria": [
        "string"
      ]
    }
  ]
}
         */

    }



    public void deleteLivro(int id){

        //tentar encontrar a ID, senão achar,já encaminhar a exceção
        Livro livro = livroRepositoryImplementation.findLivroById(id).orElseThrow(()-> new RuntimeException("Livro não encontrado com essa id!"));


    //Desassociar os livros manualmente de autor, leitor, categorias, empréstimos, para não precisar mexer
        //nos Cascades

        if(livro.getAutor() != null){
            livro.getAutor().getLivros().remove(livro);
            livro.setAutor(null);
        }

        if(livro.getLeitor() != null){
            for(Leitor leitor: livro.getLeitor()){
                leitor.getLivro().remove(livro);
            }
            livro.getLeitor().clear();
        }
        if (livro.getCategorias() != null){
            for(Categoria categoria: livro.getCategorias()){
                categoria.getLivrosCategoria().remove(livro);
            }
            livro.getCategorias().clear();
        }

        if(livro.getLivroEmprestimos() != null){
            for(Emprestimo emprestimo: livro.getLivroEmprestimos()){
                emprestimo.getLivros().remove(livro);
            }
            livro.getLivroEmprestimos().clear();
        }


            //Se o livro for encontrado, iremos removê-lo, e desassociá-lo do autor

            livroRepositoryImplementation.deleteLivro(livro.getId());
            System.out.println("Livro com a id: " + id + " deletado com sucesso!");
            }
        }



