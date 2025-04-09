package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationImplementations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation.LivroCategoriaAutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LivroCategoriaAutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.AutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.CategoriaResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.LivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.AutorAssociationService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.AutorService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.CategoriaService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.LivroService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AutorAssociationServiceImpl implements AutorAssociationService {


    @Autowired
    private AutorService autorService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private CategoriaService categoriaService;


    //Lógica funcionando OK
    @Override
    @Transactional
    public LivroCategoriaAutorResponse criarAutorComLivro(LivroCategoriaAutorRequest livroCategoriaAutorRequest) {

        //Criar um novo autor chamando o método criarAutor
        Autor novoAutor;
        try {
            novoAutor = autorService.criarAutor(new AutorRequest(livroCategoriaAutorRequest.Autor().nome(),
                    livroCategoriaAutorRequest.Autor().email(), livroCategoriaAutorRequest.Autor().telefone(),
                    livroCategoriaAutorRequest.Autor().cidade()));


        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao criar o autor: " + e.getMessage());
        }

        //criar um livro chamando o método criar livro
        Livro novoLivro;
        try{
            novoLivro = livroService.criarLivro(livroCategoriaAutorRequest.Livro());
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao criar o livro: " + e.getMessage());
        }

        //Instanciar uma categoria encontrada
        Categoria novaCategoria =  categoriaService.
                encontraCategoriaPorNome(livroCategoriaAutorRequest.Categoria().categoria());

        //associar o autor ao livro e a categoria
        try{
            novoAutor.associarAutorParaUmLivro(novoLivro);
            //associar o livro do autor a nova categoria
            novoLivro.associarLivroACategoria(novaCategoria);
            //Se essa categoria não existir..
            if(!categoriaService.encontrarTodasCategorias().contains(novaCategoria)){
                throw new NoSuchElementException("Não há nenhuma categoria com " +
                        "esse nome cadastrado no banco de dados!");
            }

            } catch (Exception e) {
            throw new RuntimeException("Erro ao associar o autor ao livro, ou a categoria ao livro.. verifique" +
                    "se digitou os dados correto, e se a categoria ja possui uma cadastrada!: " + e.getMessage());
        }


        //Mapear os dados do livro e autor criado para retornar em formato DTO ao usuário, para ele não ver todos
        //os dados irrelevantes
        AutorResponse autorResponse = new AutorResponse(novoAutor.getId(), novoAutor.getNome(),
                novoAutor.getEmail(), novoAutor.getTelefone(), novoAutor.getCidade());

        List<LivroResponse> livroResponse = Collections.singletonList(new LivroResponse(novoLivro.getId(), novoLivro.getTitulo(),
                novoLivro.getAnoLancamento()));

        List<CategoriaResponse> categoriaResponses = Collections.singletonList(new CategoriaResponse
                (novaCategoria.getId(), novaCategoria.getNomeCategoria()));

        //retornar o autor já criado com o livro associado
        return new LivroCategoriaAutorResponse(livroResponse, categoriaResponses, autorResponse);
    }


    @Override
    public LivroCategoriaAutorResponse lerAutorPorId(Integer id) {

        //Informar a Id do autor que eu quero consultar

        Optional<Autor> idAutorEncontrado = Optional.ofNullable(autorService.encontrarAutorPorId(id));

        //Se o autor não for encontrado.
        if(idAutorEncontrado.isEmpty()){
            throw new NoSuchElementException("Não foi encontrado nenhum autor associado com essa id informada!");
        }

        //Se o autor for encontrado, encontrar os livros associados a este autor
        List<Livro> todosLivrosAutor =  idAutorEncontrado.get().getListaLivrosDosAutores();


        //Obter a categoria de toda essa lista de livros, categoria de cada um
        List<LivroResponse> livroResponseList = todosLivrosAutor.stream().map(livro -> {

            livro.getListaLivrosComCategoria().forEach(categoriaLivro -> new CategoriaResponse
                    (categoriaLivro.getId(), categoriaLivro.getNomeCategoria()));

            return new LivroResponse(livro.getId(), livro.getTitulo(), livro.getAnoLancamento());
        }).toList();



        // Consolidar todas as categorias associadas aos livros
        List<CategoriaResponse> categoriaResponses = todosLivrosAutor.stream()
                .flatMap(livro -> livro.getListaLivrosComCategoria().stream())
                .distinct()
                .map(categoria -> new CategoriaResponse(categoria.getId(), categoria.getNomeCategoria()))
                .toList();


        //Cada livro encontrado, será convertido para livroResponse, para o DTO
        todosLivrosAutor.forEach(livro -> new LivroResponse(livro.getId(), livro.getTitulo(),
               livro.getAnoLancamento()));


        //Transformando em DTO o autor

        //Converter o retorno dos dados do autor para transferencia de dados(DTO)
        Autor todosDadosDoAutor = idAutorEncontrado.get();
        AutorResponse autorResponse = (new AutorResponse(todosDadosDoAutor.getId(),
                todosDadosDoAutor.getNome(), todosDadosDoAutor.getEmail(), todosDadosDoAutor.getTelefone(),
                todosDadosDoAutor.getCidade()));


//Retornar o autor, com os formulários certos de resposta baseado no autor
        return new LivroCategoriaAutorResponse(livroResponseList, categoriaResponses , autorResponse);
    }


    @Override
    public List<LivroCategoriaAutorResponse> lerTodosAutores() {

        // Encontrar todos os autores no banco de dados
        List<Autor> autoresEncontrados = autorService.encontrarTodosAutores();

        // Mapear cada autor e seus livros associados para DTOs
        List<LivroCategoriaAutorResponse> autoresComLivros = autoresEncontrados.stream()
                .map(autor -> {
                    // Mapear os livros do autor para LivroResponse
                    List<LivroResponse> livrosDoAutor = autor.getListaLivrosDosAutores().stream()
                            .map(livro -> {
                                // Mapear categorias do livro para CategoriaResponse
                                List<CategoriaResponse> categoriasDoLivro = livro.getListaLivrosComCategoria().stream()
                                        .map(categoria -> new CategoriaResponse(categoria.getId(), categoria.getNomeCategoria()))
                                        .toList();

                                // Retornar LivroResponse
                                return new LivroResponse(livro.getId(), livro.getTitulo(), livro.getAnoLancamento());
                            }).toList();

                    // Consolidar todas as categorias associadas aos livros do autor
                    List<CategoriaResponse> categoriasDoAutor = autor.getListaLivrosDosAutores().stream()
                            .flatMap(livro -> livro.getListaLivrosComCategoria().stream())
                            .distinct()
                            .map(categoria -> new CategoriaResponse(categoria.getId(), categoria.getNomeCategoria()))
                            .toList();

                    // Criar o AutorResponse
                    AutorResponse autorResponse = new AutorResponse(
                            autor.getId(),
                            autor.getNome(),
                            autor.getEmail(),
                            autor.getTelefone(),
                            autor.getCidade()
                    );

                    // Retornar LivroCategoriaAutorResponse
                    return new LivroCategoriaAutorResponse(livrosDoAutor, categoriasDoAutor, autorResponse);
                }).toList();

        // Retornar a lista de LivroCategoriaAutorResponse
        return autoresComLivros;
    }

    @Override
    @Transactional
    public LivroCategoriaAutorResponse atualizarAutorComLivroAssociado(Integer id, AutorRequest autorRequest) {
        // Encontrar o autor que será atualizado pela ID
        Autor autorEncontrado = autorService.encontrarAutorPorId(id);

        // Atualizar os dados do autor, sem alterar os livros associados
        autorService.atualizarAutor(id, autorRequest);

        // Atualizar o objeto autorEncontrado com os dados atualizados
        autorEncontrado.setNome(autorRequest.nome());
        autorEncontrado.setEmail(autorRequest.email());
        autorEncontrado.setTelefone(autorRequest.telefone());
        autorEncontrado.setCidade(autorRequest.cidade());

        // Mapear os livros do autor para LivroResponse e incluir as categorias
        List<LivroResponse> livroResponseList = autorEncontrado.getListaLivrosDosAutores().stream()
                .map(livro -> {
                    // Mapear categorias do livro para CategoriaResponse
                    List<CategoriaResponse> categoriasDoLivro = livro.getListaLivrosComCategoria().stream()
                            .map(categoria -> new CategoriaResponse(categoria.getId(), categoria.getNomeCategoria()))
                            .toList();

                    // Retornar o LivroResponse com as categorias associadas
                    return new LivroResponse(livro.getId(), livro.getTitulo(), livro.getAnoLancamento());
                }).toList();

        // Consolidar todas as categorias associadas aos livros do autor
        List<CategoriaResponse> categoriaResponses = autorEncontrado.getListaLivrosDosAutores().stream()
                .flatMap(livro -> livro.getListaLivrosComCategoria().stream())
                .distinct()
                .map(categoria -> new CategoriaResponse(categoria.getId(), categoria.getNomeCategoria()))
                .toList();

        // Criar o AutorResponse com os dados atualizados
        AutorResponse autorResponse = new AutorResponse(
                autorEncontrado.getId(),
                autorEncontrado.getNome(),
                autorEncontrado.getEmail(),
                autorEncontrado.getTelefone(),
                autorEncontrado.getCidade()
        );

        // Retornar o autor atualizado, livros associados e categorias no DTO final
        return new LivroCategoriaAutorResponse(livroResponseList, categoriaResponses, autorResponse);
    }


    @Override
    @Transactional
    public LivroCategoriaAutorResponse deletarAutorELivrosAssociados(Integer id) {
        // Encontrar o autor pela sua ID
        Autor autorEncontrado = autorService.encontrarAutorPorId(id);

        // Verificar se há livros associados
        List<Livro> livrosAssociados = autorEncontrado.getListaLivrosDosAutores();

        // Não preciso criar lógica para remover os livros, pois o CascadeType já cuida disso

        // Remover o autor do banco de dados
        autorService.deletarAutor(id);

        // Mapear os dados do autor removido
        AutorResponse autorResponse = new AutorResponse(
                autorEncontrado.getId(),
                autorEncontrado.getNome(),
                autorEncontrado.getEmail(),
                autorEncontrado.getTelefone(),
                autorEncontrado.getCidade()
        );

        // Mapear os livros do autor para LivroResponse e incluir as categorias
        List<LivroResponse> livroResponse = livrosAssociados.stream()
                .map(livro -> {
                    List<CategoriaResponse> categoriasDoLivro = livro.getListaLivrosComCategoria().stream()
                            .map(categoria -> new CategoriaResponse(categoria.getId(), categoria.getNomeCategoria()))
                            .toList();

                    return new LivroResponse(livro.getId(), livro.getTitulo(), livro.getAnoLancamento());
                })
                .toList();

        // Consolidar todas as categorias dos livros do autor
        List<CategoriaResponse> categoriaResponses = livrosAssociados.stream()
                .flatMap(livro -> livro.getListaLivrosComCategoria().stream())
                .distinct()
                .map(categoria -> new CategoriaResponse(categoria.getId(), categoria.getNomeCategoria()))
                .toList();

        // Retornar a resposta ao usuário com o autor e seus livros removidos
        return new LivroCategoriaAutorResponse(livroResponse, categoriaResponses, autorResponse);
    }

}