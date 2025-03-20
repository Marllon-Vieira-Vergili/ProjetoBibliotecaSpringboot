package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.*;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.AutorRepositoryImplement;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.LivroRepositoryImplementation;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LivroService implements Serializable {


    //Instanciando apenas o Repositório de métodos para o Livro, e Injetando
    @Autowired
    private LivroRepositoryImplementation livroRepositoryImplementation;

    @Autowired
    private AutorRepositoryImplement autorRepositoryImplement;

    public LivroService(LivroRepositoryImplementation livroRepositoryImplementation) {
        this.livroRepositoryImplementation = livroRepositoryImplementation;
    }

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


        //Se o autor não for informado, será criado um novo autor
        if (livro.getAutor() == null) {
            throw new NullPointerException("O autor não pode ser nulo! um livro precisa de um autor!");
        }


        //Verificar se o autor existe no banco de dados
        Optional<Autor> autorExistente = autorRepositoryImplement.findAutorById(livro.getAutor().getId());
        if (autorExistente.isEmpty()) {
            throw new IllegalArgumentException("O autor informado não existe no banco de dados!");
        }

        //associar o autor existente ao livro
        livro.setAutor(autorExistente.get());
       livroRepositoryImplementation.saveLivro(livro);

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





    public Optional<Livro> findLivroById(int id) {

        Livro livro = livroRepositoryImplementation.findLivroById(id).orElseThrow(() -> new NoSuchElementException("Não há nenhum livro com esse id!"));

        return livroRepositoryImplementation.findLivroById(id);
    }



    public List<Livro> findAllivro(){

        try{
            return livroRepositoryImplementation.findAllLivro();
        }catch (NoSuchElementException e){
            return null;
        }
    }


    public Optional<Livro> encontrarLivroPeloTitulo(String titulo){

        try{
            return livroRepositoryImplementation.findByNome(titulo);

        }catch (NoSuchElementException e){
            return null;
        }

           /*
        //Verificar se o livro existe pelo título
        String jpqlQuery = "SELECT l from livro WHERE l.nome = :nome";
        TypedQuery<Livro> query = entityManager.createQuery(jpqlQuery, Livro.class);

        //Se o livro existir, voltar o nome do livro
        query.setParameter("nome", nome);
        try {
            //Retornar o livro encontrado
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            System.out.println("Livro não encontrado!");
            return null;
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



