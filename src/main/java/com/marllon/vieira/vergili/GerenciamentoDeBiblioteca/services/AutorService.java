package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.AutorRepositoryImplement;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.LivroRepository;
import jakarta.persistence.EntityManager;
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
public class AutorService implements Serializable {


    //Instanciando apenas o Repositório de métodos para o autor, e Injetando
    @Autowired
    private AutorRepositoryImplement autorRepositoryImplement;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EntityManager entityManager;


    //Construtor
    public AutorService(AutorRepositoryImplement autorRepositoryImplement){
        this.autorRepositoryImplement = autorRepositoryImplement;
    }


    //TODOS OS MÈTODOS


    //Método funcionando ok!
    public void saveAutor(Autor autor) {


        //Verificar se os dados passados para a criação do autor não serão nulos, ou se já existe,
        //Quando instanciarmos em outro nosso método..

        if(autor == null){
            throw new IllegalArgumentException("O autor não pode ser nulo!");
        }
        if(autor.getNome() == null || autor.getNome().isEmpty()){
            throw new IllegalArgumentException("O nome do autor não pode ser nulo!");
        }
        if (autor.getTelefone() == null || autor.getTelefone().isEmpty()){
            throw new IllegalArgumentException("O Telefone do autor é obrigatório!");
        }
        if (autor.getCidade() == null || autor.getCidade().isEmpty()){
            throw new IllegalArgumentException("A cidade do autor é obrigatório!");
        }
        if (autor.getEmail() == null || autor.getEmail().isEmpty()){
            throw new IllegalArgumentException("O Email do autor é obrigatório!");
        }


        //Verificar se ja existirá um autor com os mesmos dados, através de seu email
        Optional<Autor> autorExiste = autorRepositoryImplement.findByEmail(autor.getEmail());

       if (autorExiste.isPresent()) {
           throw new IllegalStateException("Já existe um autor com esse e-mail cadastrado: " + autor.getEmail());
       }

        //Instanciar um novo autor
        Autor novoAutor = new Autor();
        novoAutor.setNome(autor.getNome());
        novoAutor.setEmail(autor.getEmail());
        novoAutor.setTelefone(autor.getTelefone());
        novoAutor.setCidade(autor.getCidade());

        //salvar o autor no banco de dados
        try{
            autorRepositoryImplement.saveAutor(novoAutor);
            System.out.println("Autor cadastrado com sucesso! Seguem os novos dados do autor adicionado:  " + novoAutor);
        } catch (Exception e){
            throw new IllegalStateException("Erro ao salvar o autor!" + e.getMessage());
        }
    }





//Método Funcionando ok!
    public Optional<Autor> findAutorById(int autorId) {

        //Verificar primeiro, se a Id do autor existe.
        if (autorId < 0 ) {
           throw new NoSuchElementException("Id do autor inválida!");
        }

        //Vamos instanciar o autor, Encontrando ele pela Id que for passada.
        Optional <Autor> autor = autorRepositoryImplement.findAutorById(autorId);

        //Vamos agora verificar se o autor existe.
        if(autor.isEmpty()){
            throw new NoSuchElementException("Nenhum autor encontrado com essa ID: " + autorId);
        }

        //Se o autor for encontrado, vamos carregar ele
        Autor foundAutor = autor.get();
        System.out.println("Autor encontrado com a id: " + autorId + " Segue os dados do autor: " + foundAutor);


        //Vamos verificar se o autor tem livros associados
        if(foundAutor.getLivros().isEmpty() || foundAutor.getLivros() == null){
            System.out.println("Este autor não possui livros associados a ele!" + foundAutor);
            foundAutor.setLivros(new ArrayList<>());
        }
        else{
            //Se houver livros associados, vamos imprimir a lista de livros associados
            System.out.println("Este autor tem: " + foundAutor.getLivros().size() + " livros associados a ele");
            System.out.println("Os livros são: " + foundAutor.getLivros());
        }



        //retornar o autor encontrado
        return Optional.of(foundAutor);
    }


    //Método funcionando ok
    public List<Autor> findAllAutor(){

        if (autorRepositoryImplement.findAllAutor().isEmpty()){
            throw new NoSuchElementException("Não há nenhum elemento nesta lista de autor!");
        }
        return autorRepositoryImplement.findAllAutor();

    }

//Método funcionando OK!
    public void updateAutor(int id, Autor autor){

        //Verificar se os a id do autor que eu quero atualizar existe no banco de dados
        Optional<Autor> existingAutor = autorRepositoryImplement.findAutorById(id);

        if (existingAutor.isEmpty()){
            throw new NoSuchElementException("Autor não encontrado com essa ID!" + id);
        }

        //verificar se já possuirá algum autor, verificando pelo mesmo email
        //Optional<Autor> emailAutor = autorRepositoryImplement.findByEmail(autor.getEmail());
        //if(emailAutor.isPresent() && emailAutor.get().getId() != id){
            //throw new IllegalStateException("Já existe um autor cadastrado com este email");
        //}


        //verificar se os dados estão corretos para salvar no banco de dados
        if(autor.getNome() == null || autor.getNome().isEmpty()|| autor.getNome().isBlank()){
            throw new IllegalArgumentException("O nome do autor não pode ser vazio");
        }

        else if(autor.getCidade() == null || autor.getCidade().isEmpty()|| autor.getNome().isBlank()){
            throw new IllegalArgumentException("O nome da cidade não pode ser vazio");
        }

        else if(autor.getEmail() == null || autor.getEmail().isEmpty()|| autor.getEmail().isBlank()){
            throw new IllegalArgumentException("O email não pode ser vazio!");
        }

        else if(autor.getTelefone() == null || autor.getTelefone().isEmpty()|| autor.getTelefone().isBlank()){
            throw new IllegalArgumentException("O Telefone não pode ficar vazio!");
        }

            //atualizar os dados no banco de dados
            Autor updatedAutor = existingAutor.get();
            updatedAutor.setNome(autor.getNome());
            updatedAutor.setTelefone(autor.getTelefone());
            updatedAutor.setCidade(autor.getCidade());
            updatedAutor.setEmail(autor.getEmail());
            autorRepositoryImplement.saveAutor(updatedAutor);
            System.out.println("Autor atualizado com sucesso!: " + updatedAutor);
        }

//Método funcionando ok!
    public void deleteAutor(int id){

        //Verificar se a id do autor existe
        Optional<Autor> autorOptional = autorRepositoryImplement.findAutorById(id);
        if (autorOptional.isEmpty()){
            throw new IllegalArgumentException("Id não foi encontrada!");
        }

        Autor autor = autorOptional.get();

        //Se o autor existir, mas não tiver nenhum livro com ele, excluir só o autor
        if(autor.getLivros() == null && autor.getLivros().isEmpty()){
            autorRepositoryImplement.deleteAutor(id);
            System.out.println("Somente autor deletado, pois o mesmo não possui livros");

        }

        autorRepositoryImplement.deleteAutor(id);
    System.out.println("Autor: " + autor.getNome() + " Deletado com sucesso!");
    System.out.println("Livros associados ao autor deletados com sucesso! " + autor.getLivros());
    }



    public Optional<Autor> findAutorByEmail(String email){

                try{

                    return autorRepositoryImplement.findByEmail(email);

                }catch (NoSuchElementException e){
                    return null;
                }
              /*
        //String jpql = "SELECT a FROM Autor a WHERE a.email =: email";
        TypedQuery<Autor> query = entityManager.createQuery("SELECT a FROM autor WHERE a.email =: email ", Autor.class);

        query.setParameter("a.email", email);

        try {
            Autor autor = query.getSingleResult();
            return Optional.ofNullable((autor));

        } catch (Exception e) {
            return Optional.empty();
        }
         */
    }
}

