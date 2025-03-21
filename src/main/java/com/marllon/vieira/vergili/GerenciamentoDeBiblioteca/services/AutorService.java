package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.dto.AutorDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.AutorRepositoryImplement;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.LivroRepository;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


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

    @Autowired
    private ModelMapper modelMapper; //Fazer com que minhas DTOs peguei dados da Entity


    //TODOS OS MÈTODOS


    //Método funcionando ok!
    public AutorDTO saveAutor(AutorDTO autor) {

        //Verificar se os dados passados para a criação do autor não serão nulos, ou se já existe,
        //Quando instanciarmos em outro nosso método..

        if (autor == null) {
            throw new IllegalArgumentException("O autor não pode ser nulo!");
        }
        if (autor.getNome() == null || autor.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do autor não pode ser nulo!");
        }
        if (autor.getTelefone() == null || autor.getTelefone().isEmpty()) {
            throw new IllegalArgumentException("O Telefone do autor é obrigatório!");
        }
        if (autor.getCidade() == null || autor.getCidade().isEmpty()) {
            throw new IllegalArgumentException("A cidade do autor é obrigatório!");
        }
        if (autor.getEmail() == null || autor.getEmail().isEmpty()) {
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
        try {
            autorRepositoryImplement.saveAutor(novoAutor);
            System.out.println("Autor cadastrado com sucesso! Seguem os novos dados do autor adicionado:  " + novoAutor);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao salvar o autor!" + e.getMessage());
        }

        //Usar o modelmapper para converter a entidade autor para DTO
        AutorDTO autorDTO = modelMapper.map(novoAutor, AutorDTO.class);

        //retornar o DTO para ser enviado como JSON
        return autorDTO;


    }


    //Método Funcionando ok!
    public AutorDTO findAutorById(int autorId) {


        //Verificar primeiro, se a Id passada será menor ou igual a 0
        if (autorId <= 0) {
            throw new NoSuchElementException("Id do autor inválida!");
        }

        //Vamos instanciar o autor, Encontrando ele pela Id que for passada.
        Optional<Autor> autor = autorRepositoryImplement.findAutorById(autorId);

        //Vamos agora verificar se o autor existe.
        if (autor.isEmpty()) {
            throw new NoSuchElementException("Nenhum autor encontrado com essa ID: " + autorId);
        }

        //Se o autor for encontrado, vamos carregar ele
        Autor foundAutor = autor.get();
        System.out.println("Autor encontrado com a id: " + autorId + " Segue os dados do autor: " + foundAutor);


        //Vamos verificar se o autor tem livros associados
        if (foundAutor.getLivros().isEmpty() || foundAutor.getLivros() == null) {
            System.out.println("Este autor não possui livros associados a ele!" + foundAutor);
            foundAutor.setLivros(new ArrayList<>());
        } else {
            //Se houver livros associados, vamos imprimir a lista de livros associados
            System.out.println("Este autor tem: " + foundAutor.getLivros().size() + " livros associados a ele");
            System.out.println("Os livros são: " + foundAutor.getLivros());
        }

        //Converter o mapeamento de Autor para Autor dto
        AutorDTO autorConvertido = modelMapper.map(foundAutor, AutorDTO.class);

        //retornar o autor encontrado
        return autorConvertido;
    }


    //Método funcionando ok
    public List<AutorDTO> findAllAutor() {

        //Instanciar uma lista de autores
        List<Autor> listaAutores = autorRepositoryImplement.findAllAutor();

        //Se não possuir nenhum autor na lista
        if (listaAutores.isEmpty()) {
            throw new NoSuchElementException("Não há nenhum elemento nesta lista de autor!");
        }

//Converter todos os autores da lista para um autor DTO, usando modelmapper
List<AutorDTO> listaautoresDTO = listaAutores.stream().map(autor -> modelMapper.map(autor, AutorDTO.class)).toList();


        //Se possuir autor na lista, mostrar todos

        return listaautoresDTO;
    }


    //Método funcionando OK!
    public AutorDTO updateAutor(int id, AutorDTO autor) {

        //Verificar se os a id do autor que eu quero atualizar existe no banco de dados
        Optional<Autor> existingAutor = autorRepositoryImplement.findAutorById(id);

        if (existingAutor.isEmpty()) {
            throw new NoSuchElementException("Autor não encontrado com essa ID!" + id);
        }

        //verificar se já possuirá algum autor, verificando pelo mesmo email
        //Optional<Autor> emailAutor = autorRepositoryImplement.findByEmail(autor.getEmail());
        //if(emailAutor.isPresent() && emailAutor.get().getId() != id){
        //throw new IllegalStateException("Já existe um autor cadastrado com este email");
        //}


        //verificar se os dados estão corretos para salvar no banco de dados
        if (autor.getNome() == null || autor.getNome().isEmpty() || autor.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do autor não pode ser vazio");
        } else if (autor.getCidade() == null || autor.getCidade().isEmpty() || autor.getCidade().isBlank()) {
            throw new IllegalArgumentException("O nome da cidade não pode ser vazio");
        } else if (autor.getEmail() == null || autor.getEmail().isEmpty() || autor.getEmail().isBlank()) {
            throw new IllegalArgumentException("O email não pode ser vazio!");
        } else if (autor.getTelefone() == null || autor.getTelefone().isEmpty() || autor.getTelefone().isBlank()) {
            throw new IllegalArgumentException("O Telefone não pode ficar vazio!");
        }

        //atualizar os dados no banco de dados
        Autor updatedAutor = existingAutor.get();
        updatedAutor.setNome(autor.getNome());
        updatedAutor.setTelefone(autor.getTelefone());
        updatedAutor.setCidade(autor.getCidade());
        updatedAutor.setEmail(autor.getEmail());

        //Salvar o autor atualizado
        autorRepositoryImplement.saveAutor(updatedAutor);

        //Converter todos os dados do meu autor, para o AutorDTO, para aparecer no JSON
        System.out.println("Autor atualizado com sucesso!: " + updatedAutor);
        return modelMapper.map(updatedAutor, AutorDTO.class);
    }

    //Método funcionando ok!
    public void deleteAutor(int id) {

        //Verificar se a id do autor existe
        Optional<Autor> autorexiste = autorRepositoryImplement.findAutorById(id);
        if (autorexiste.isEmpty()) {
            throw new IllegalArgumentException("Id não foi encontrada!");
        }

        Autor autor = autorexiste.get();

        //Mapear os dados do nosso autor que será deletado para o autorDAO
        AutorDTO autorDTO = modelMapper.map(autor, AutorDTO.class);

        //Se o autor existir, mas não tiver nenhum livro com ele, excluir só o autor
        if (autor.getLivros() == null && autor.getLivros().isEmpty()) {
            System.out.println("Somente autor deletado, pois o mesmo não possui livros");
        }

        autorRepositoryImplement.deleteAutor(id);

        System.out.println("Autor: " + autor.getNome() + " Deletado com sucesso!");
        System.out.println("Livros associados ao autor deletados com sucesso! " + autor.getLivros());
    }


    //funcionou
    public Optional<AutorDTO> findAutorByEmail(String email) {

//Se o autor não for encontrado pelo email
        if (autorRepositoryImplement.findByEmail(email).isEmpty()) {
            throw new NoSuchElementException("Não há nenhum autor associado a esse email!");
        }

        Optional<Autor> autorEncontradoPorEmail = autorRepositoryImplement.findByEmail(email);

        AutorDTO autorDTO = modelMapper.map(autorEncontradoPorEmail,AutorDTO.class);

        return Optional.ofNullable(autorDTO);
    }
}



