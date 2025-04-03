package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationImplementations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.AutorRequestComLivro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.AutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.AutorComLivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.AutorAssociationLivroService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.AutorService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.LivroService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AutorLivroService implements AutorAssociationLivroService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private AutorService autorService;

    @Autowired
    private LivroService livroService;


    //Lógica funcionando OK
    @Override
    @Transactional
    public AutorComLivroResponse criarAutorComLivro(AutorRequestComLivro autorRequestComLivro) {

        //Criar um novo autor chamando o método criarAutor
        Autor novoAutor;
        try {
            novoAutor = autorService.criarAutor(new AutorRequest(autorRequestComLivro.nome(), autorRequestComLivro.email(),
                    autorRequestComLivro.telefone(), autorRequestComLivro.telefone()));

        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao criar o autor: " + e.getMessage());
        }


        //criar um livro
        Livro novoLivro;
        try{
            novoLivro = livroService.criarLivro(autorRequestComLivro.Livro());
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao criar o livro: " + e.getMessage());
        }

        //associar o autor ao livro
        try{
            novoAutor.associarAutorParaUmLivro(novoLivro);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao associar o autor ao livro: " + e.getMessage());
        }

        //Salvar o autor no banco de dados
        autorRepository.save(novoAutor);

        //Mapear os dados do livro e autor criado para retornar em formato DTO ao usuário, para ele não ver todos
        //os dados irrelevantes
        AutorResponse autorResponse = new AutorResponse(novoAutor.getId(), novoAutor.getNome(),
                novoAutor.getEmail(), novoAutor.getTelefone(), novoAutor.getCidade());

        List<LivroResponse> livroResponse = Collections.singletonList(new LivroResponse(novoLivro.getId(), novoLivro.getTitulo(),
                novoLivro.getAnoLancamento()));

        //retornar o autor já criado com o livro associado
        return new AutorComLivroResponse(autorResponse, livroResponse);
    }


    @Override
    public AutorComLivroResponse lerAutorPorId(Integer id) {

        //Informar a Id do autor que eu quero consultar
        Optional<Autor> idAutorEncontrado = autorRepository.findById(id);

        //Se o autor não for encontrado.
        if(idAutorEncontrado.isEmpty()){
            throw new NoSuchElementException("Não foi encontrado nenhum autor associado com essa id informada!");
        }

        //Se o autor for encontrado, encontrar os livros associados a este autor
        List<Livro> todosLivrosAutor =  idAutorEncontrado.get().getListaLivrosDosAutores();

        //Converter os livros pada DTO
        todosLivrosAutor.forEach(livro -> new LivroResponse(livro.getId(), livro.getTitulo(),
                livro.getAnoLancamento()));

        List<LivroResponse> livroResponseConvertido = todosLivrosAutor.stream().map(livro -> new LivroResponse
                (livro.getId(), livro.getTitulo(), livro.getAnoLancamento())).toList();


        //Transformando em DTO o autor

        //Converter o retorno dos dados do autor para transferencia de dados(DTO)
        Autor todosDadosDoAutor = idAutorEncontrado.get();
        AutorResponse autorResponse = (new AutorResponse(todosDadosDoAutor.getId(),
                todosDadosDoAutor.getNome(), todosDadosDoAutor.getEmail(), todosDadosDoAutor.getTelefone(),
                todosDadosDoAutor.getCidade()));


//Retornar o autor, com os formulários certos de resposta baseado no autor
        return new AutorComLivroResponse(autorResponse, livroResponseConvertido);
    }

    @Override
    public List<AutorComLivroResponse> lerTodosAutores() {

        // Encontrar todos os autores no banco de dados
        List<Autor> autoresEncontrados = autorRepository.findAll();

        // Mapear cada autor e seus livros associados para DTOs
        List<AutorComLivroResponse> autoresComLivros = autoresEncontrados.stream()
                .map(autor -> {
                    // Mapear os livros do autor para LivroResponse
                    List<LivroResponse> livrosDoAutor = autor.getListaLivrosDosAutores().stream()
                            .map(livro -> new LivroResponse(livro.getId(), livro.getTitulo(), livro.getAnoLancamento()))
                            .toList();

                    // Criar o AutorResponse
                    AutorResponse autorResponse = new AutorResponse(
                            autor.getId(),
                            autor.getNome(),
                            autor.getEmail(),
                            autor.getTelefone(),
                            autor.getCidade()
                    );

                    // Retornar AutorComLivroResponse
                    return new AutorComLivroResponse(autorResponse, livrosDoAutor);
                }) .toList();

        // Retornar a lista de AutorComLivroResponse
        return autoresComLivros;
    }

    @Override
    @Transactional
    public AutorComLivroResponse atualizarAutorComLivroAssociado(Integer id, AutorRequest autorRequest) {

        // Encontrar o autor que será atualizado pela id
        Autor autorEncontrado = autorRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum autor encontrado com essa id!"));

        //inserir os novos dados do autor, sem mexer nos livros já associados a ele
        autorEncontrado.setNome(autorRequest.nome());
        autorEncontrado.setEmail(autorRequest.email());
        autorEncontrado.setTelefone(autorRequest.telefone());
        autorEncontrado.setCidade(autorRequest.cidade());

        //Verificar se os novos dados serão válidos
        if (autorEncontrado.getNome() == null || autorEncontrado.getNome().isEmpty() || autorEncontrado.getNome().isBlank() || !autorEncontrado.getNome()
                .matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("O nome do autor deve conter apenas letras e espaços!");
        }

        if (autorEncontrado.getEmail() == null || autorEncontrado.getEmail().isEmpty() || autorEncontrado.getEmail().isBlank() || !autorEncontrado.getEmail()
                .matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("O email do autor deve conter @!");
        }

        if (autorEncontrado.getTelefone() == null ||autorEncontrado.getTelefone().isEmpty() || autorEncontrado.getTelefone().isBlank() || !autorEncontrado.getTelefone()
                .matches("\\d+")|| autorEncontrado.getTelefone().length() != 11) {
            throw new IllegalArgumentException("O telefone do autor deve conter apenas números, com 11 digitos incluindo DDD padrão Brasileiro!!");
        }

        if (autorEncontrado.getCidade() == null ||autorEncontrado.getCidade().isEmpty() || autorEncontrado.getCidade().isBlank() || !autorEncontrado.getCidade()
                .matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("A cidade do autor deve conter apenas caracteres e espaços!!");
        }

        //Se passar das condições e estiver certo, vamos salvar esse novo autor
        autorRepository.save(autorEncontrado);

        //mapear os dados do novo autor atualizado e converter da entidade autor para DTO
       AutorResponse autorResponse = new AutorResponse(autorEncontrado.getId(), autorEncontrado.getNome(),
               autorEncontrado.getEmail(), autorEncontrado.getTelefone(), autorEncontrado.getCidade());

        List<LivroResponse> livroResponseList = autorEncontrado.getListaLivrosDosAutores()
                .stream().map(livro -> new LivroResponse(livro.getId(), livro.getTitulo(),
                        livro.getAnoLancamento())).toList();

        //retornar o autor atualizado, e seus livros associados, pela DTO
        return new AutorComLivroResponse(autorResponse, livroResponseList);
    }

    @Override
    @Transactional
    public AutorComLivroResponse deletarAutorELivrosAssociados(Integer id) {

        //Encontrar o autor pela sua id
        Autor autorEncontrado = autorRepository.findById(id).orElseThrow(() ->
                        new NoSuchElementException("Nenhum autor foi encontrado com essa id!"));

        //verificar se há livros associados, pegar os livros do autor
        List<Livro> livrosAssociados = autorEncontrado.getListaLivrosDosAutores();

        //Não preciso criar lógica pra remover os livros, pois ja associei no cascadeTYpe do autor para livros

        //remover o autor do banco de dados
        autorRepository.delete(autorEncontrado);

        //Mapear os dados do autor removido, e seus livros também
        AutorResponse autorResponse = new AutorResponse(autorEncontrado.getId(), autorEncontrado.getNome(),
                autorEncontrado.getEmail(), autorEncontrado.getTelefone(), autorEncontrado.getCidade());

        List<LivroResponse> livroResponse = livrosAssociados.stream().map(livro -> new LivroResponse
                (livro.getId(), livro.getTitulo(), livro.getAnoLancamento())).toList();

        //retornar um nova resposta ao usuário do que foi removido do banco
        return new AutorComLivroResponse(autorResponse,livroResponse);
    }

}