package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationImplementations;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.CategoriaRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.CategoriaLivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.CategoriaResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.LivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.CategoriaRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LivroRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.CategoriaAssociationService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.AutorService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.CategoriaService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


@Service
public class CategoriaAssociationServiceImpl implements CategoriaAssociationService {

    @Autowired
    private CategoriaService categoriaService;




    @Autowired
    private LivroService livroService;


    @Override
    public CategoriaResponse criarCategoria(CategoriaRequest categoriaRequest) {
        Categoria novaCategoria = categoriaService.criarCategoria(categoriaRequest);

        return new CategoriaResponse(novaCategoria.getId(), novaCategoria.getNomeCategoria());
    }

    @Override
    public CategoriaLivroResponse encontrarUmaCategoriaELivros(Integer id) {
        //Encontrar a id da categoria
        Categoria categoriaEncontrada = categoriaService.encontrarCategoriaPorId(id);

        //Encontrar os livros associados a ela
        List<Livro>  livrosEncontrados = categoriaEncontrada.getListaLivrosRelacionados();

        //Converter os dados em DTO
        CategoriaResponse categoriaResponse = new CategoriaResponse(categoriaEncontrada.getId(),
                categoriaEncontrada.getNomeCategoria());

        List<LivroResponse> livroResponseList = livrosEncontrados.stream().map(livro -> new LivroResponse
                (livro.getId(), livro.getTitulo(), livro.getAnoLancamento())).toList();


        //Retornar ao usuário
        return new CategoriaLivroResponse(categoriaResponse, livroResponseList);
    }

    @Override
    public List<CategoriaLivroResponse> encontrarTodasCategoriasELivrosAssociados() {
        //Encontrar todas as categorias
        List<Categoria> todasCategorias = categoriaService.encontrarTodasCategorias();


        //Converter os dtos dentro do for para array List
        List<CategoriaLivroResponse> livroCategoria = new ArrayList<>();

        //Encontrar os livros associados a todas as categorias
        for(Categoria categoriasPercorridas: todasCategorias){
            List<Livro> livrosRelacionados = categoriasPercorridas.getListaLivrosRelacionados();

            //verificar que há livros relacionados antes de acessar
            List<LivroResponse> livroResponseList = livrosRelacionados.stream().map(livro ->
                    new LivroResponse(livro.getId(), livro.getTitulo(), livro.getAnoLancamento())).toList();

            //verificar apenas a categoria atual
            CategoriaResponse categoriaList = new CategoriaResponse(categoriasPercorridas.getId(),
                    categoriasPercorridas.getNomeCategoria());

            //Retornar os valores,e adicionar no novo array list acima, para retornar fora do loop
            livroCategoria.add(new CategoriaLivroResponse(categoriaList,livroResponseList));

        }

        //Retornar todas as categorias e seus livros
        return livroCategoria;
    }

    @Override
    public CategoriaLivroResponse atualizarCategoria(Integer id, CategoriaRequest categoriaRequest) {

        //Encontrar a categoria pela id
        Categoria categoriaEncontrada = categoriaService.encontrarCategoriaPorId(id);


        //Atualizar a categoria nova
        categoriaService.atualizarCategoria(categoriaEncontrada.getId(), categoriaRequest);

        categoriaEncontrada = categoriaService.encontrarCategoriaPorId(id);

        //Converter os valores dos livros e categoria para mostrar junto com a nova categoria

        CategoriaResponse categoriaResponse = new CategoriaResponse(categoriaEncontrada.getId(),
                categoriaEncontrada.getNomeCategoria());

        List<Livro> livrosEncontrados = categoriaEncontrada.getListaLivrosRelacionados();
        List<LivroResponse> livrosResponse = livrosEncontrados.stream().map(livro -> new LivroResponse(livro.getId(),
                livro.getTitulo(), livro.getAnoLancamento())).toList();

        //Retornar a nova categoria e seus livros associados
        return new CategoriaLivroResponse(categoriaResponse, livrosResponse);
    }

    @Override
    public CategoriaLivroResponse removerCategoriaEdesassociarDosLivros(Integer id) {

        //Encontrar a id da categoria
        Categoria categoriaEncontrada = categoriaService.encontrarCategoriaPorId(id);


        //Encontrar a lista de livros relacionados a essa categoria que será removida
        List<Livro> livrosAssociados = new ArrayList<>(categoriaEncontrada.getListaLivrosRelacionados());

        //Remover a categoria, sem remover os livros que estavam associados a essa categoria
        for(Livro livrosRelacionados: livrosAssociados){
            livrosRelacionados.getListaLivrosComCategoria().remove(categoriaEncontrada);
        }

        //Limpar os livros
        categoriaEncontrada.getListaLivrosRelacionados().clear(); //limpar do lado categoria também, bidirecionalmente

    //Deletar a categoria
        categoriaService.deletarCategoria(categoriaEncontrada.getId());

        //Converter os valores dos livros e categoria para mostrar junto com a nova categoria


        CategoriaResponse categoriaResponse = new CategoriaResponse(categoriaEncontrada.getId(),
                categoriaEncontrada.getNomeCategoria());

        List<Livro> livrosEncontrados = categoriaEncontrada.getListaLivrosRelacionados();
        List<LivroResponse> livrosResponse = livrosEncontrados.stream().map(livro -> new LivroResponse(livro.getId(),
                livro.getTitulo(), livro.getAnoLancamento())).toList();

        //Retornar a nova categoria e seus livros associados
        return new CategoriaLivroResponse(categoriaResponse, livrosResponse);
    }

    @Override
    public CategoriaLivroResponse adicionarCategoriaAoLivro(Integer categoriaId, Integer livroId) {

        // Encontrar a categoria pela ID
        Categoria categoriaEncontrada = categoriaService.encontrarCategoriaPorId(categoriaId);

        // Encontrar o livro pela ID
        Livro livroEncontrado = livroService.encontrarLivroPorId(livroId);

        // Verifica se a categoria já está associada ao livro
        if (!livroEncontrado.getListaLivrosComCategoria().contains(categoriaEncontrada)) {
            // Associa a categoria ao livro e vice-versa (bidirecional)
            livroEncontrado.getListaLivrosComCategoria().add(categoriaEncontrada);
            categoriaEncontrada.getListaLivrosRelacionados().add(livroEncontrado);
        }

        // Salvar os dois lados (importante)
        livroService.salvarLivro(livroEncontrado);
        categoriaService.salvarCategoria(categoriaEncontrada);

        // Criar resposta
        CategoriaResponse categoriaResponse = new CategoriaResponse(categoriaEncontrada.getId(),
                categoriaEncontrada.getNomeCategoria());

        LivroResponse livroResponse = new LivroResponse(
                livroEncontrado.getId(),
                livroEncontrado.getTitulo(),
                livroEncontrado.getAnoLancamento());

        return new CategoriaLivroResponse(categoriaResponse, List.of(livroResponse));
    }


    @Override
    public CategoriaLivroResponse removerCategoriaAoLivro(Integer livroId, Integer categoriaId) {

        //Encontrar a categoria pela id
        Categoria categoriaEncontrada = categoriaService.encontrarCategoriaPorId(categoriaId);

        //encontrar o livro pela id
        Livro livroEncontrado = livroService.encontrarLivroPorId(livroId);

        //Se a categoria não estiver associada a um livro
        if(!livroEncontrado.getListaLivrosComCategoria().contains(categoriaEncontrada)){
            throw new IllegalStateException("Essa categoria não está associada a esse livro!");
        }

        //Remover as duas associacoes bidirecionais

        livroEncontrado.getListaLivrosComCategoria().remove(categoriaEncontrada); //remover a categoria do lado do livro
        categoriaEncontrada.getListaLivrosRelacionados().remove(livroEncontrado); //remover o livro do lado categoria

        //salvar a altereção
        livroService.salvarLivro(livroEncontrado);
        categoriaService.salvarCategoria(categoriaEncontrada);

        //Converter os valores para Dto
        CategoriaResponse categoriaResponse = new CategoriaResponse(categoriaEncontrada.getId(),
                categoriaEncontrada.getNomeCategoria());

        List<LivroResponse> livroResponseList = Collections.singletonList(new LivroResponse(livroEncontrado.getId(),
                livroEncontrado.getTitulo(), livroEncontrado.getAnoLancamento()));


        //Retornar para o usuário a resposta dto
        return new CategoriaLivroResponse(categoriaResponse,livroResponseList);
    }

}
