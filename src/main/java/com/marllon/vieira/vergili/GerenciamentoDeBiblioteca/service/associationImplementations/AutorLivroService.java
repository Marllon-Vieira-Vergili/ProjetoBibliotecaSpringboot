package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationImplementations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation.AutorRequestComLivro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.AutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.LivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.AutorComLivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
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
                    autorRequestComLivro.telefone(), autorRequestComLivro.cidade()));

        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao criar o autor: " + e.getMessage());
        }

        //criar um livro chamando o método criar livro
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

        Optional<Autor> idAutorEncontrado = Optional.ofNullable(autorService.encontrarAutorPorId(id));

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
        List<Autor> autoresEncontrados = autorService.encontrarTodosAutores();

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
        Autor autorEncontrado = autorService.encontrarAutorPorId(id);

        //inserir os novos dados do autor, sem mexer nos livros já associados a ele
        autorService.atualizarAutor(id, autorRequest);

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
        Autor autorEncontrado = autorService.encontrarAutorPorId(id);

        //verificar se há livros associados, pegar os livros do autor
        List<Livro> livrosAssociados = autorEncontrado.getListaLivrosDosAutores();

        //Não preciso criar lógica pra remover os livros, pois ja associei no cascadeType do autor para livros

        //remover o autor do banco de dados
        autorService.deletarAutor(id);

        //Mapear os dados do autor removido, e seus livros também
        AutorResponse autorResponse = new AutorResponse(autorEncontrado.getId(), autorEncontrado.getNome(),
                autorEncontrado.getEmail(), autorEncontrado.getTelefone(), autorEncontrado.getCidade());

        List<LivroResponse> livroResponse = livrosAssociados.stream().map(livro -> new LivroResponse
                (livro.getId(), livro.getTitulo(), livro.getAnoLancamento())).toList();

        //retornar um nova resposta ao usuário do que foi removido do banco
        return new AutorComLivroResponse(autorResponse,livroResponse);
    }

}