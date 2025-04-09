package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationImplementations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LivroRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation.LivroCategoriaAutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LivroAssociationsResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LivroCategoriaAutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.*;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.*;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.LivroAssociationService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.AutorService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.CategoriaService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.LivroService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LivroAssociationServiceImpl implements LivroAssociationService {


    @Autowired
    private LivroService livroService;

    @Autowired
    private AutorService autorService;

    @Autowired
    CategoriaService categoriaService;




    @Override
    @Transactional
    public LivroCategoriaAutorResponse criarLivroComAutor(LivroCategoriaAutorRequest livroCategoriaAutorRequest) {
        // Criar um novo Livro usando o serviço
        Livro novoLivro = livroService.criarLivro(new LivroRequest(
                livroCategoriaAutorRequest.Livro().titulo(),
                livroCategoriaAutorRequest.Livro().anoLancamento()));

        // Criar o autor
        Autor novoAutor = autorService.criarAutor(livroCategoriaAutorRequest.Autor());

        try {
            // Associar o livro ao autor
            novoLivro.associarLivroParaUmAutor(novoAutor);

            // Associar o livro a uma nova categoria
            Categoria novaCategoria = categoriaService.encontraCategoriaPorNome(
                    livroCategoriaAutorRequest.Categoria().categoria());

            novoLivro.associarLivroACategoria(novaCategoria);

        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Erro: Não foi possível encontrar ou associar a categoria ao livro. Verifique os dados e tente novamente.");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao associar o autor ou a categoria ao livro: " + e.getMessage());
        }

        // Mapear as categorias do livro em CategoriaResponse
        List<CategoriaResponse> categoriaResponses = novoLivro.getListaLivrosComCategoria().stream()
                .map(categoria -> new CategoriaResponse(categoria.getId(), categoria.getNomeCategoria()))
                .toList();

        // Converter os dados do autor para DTO
        AutorResponse autorResponse = new AutorResponse(
                novoAutor.getId(),
                novoAutor.getNome(),
                novoAutor.getEmail(),
                novoAutor.getTelefone(),
                novoAutor.getCidade());

        // Converter os dados do livro para DTO
        List<LivroResponse> livroResponseList = Collections.singletonList(new LivroResponse(
                novoLivro.getId(),
                novoLivro.getTitulo(),
                novoLivro.getAnoLancamento()));

        // Retornar a resposta ao usuário no formato DTO
        return new LivroCategoriaAutorResponse(livroResponseList, categoriaResponses, autorResponse);
    }

    @Override
    public LivroAssociationsResponse encontarUmLivro(Integer id) {
        //Encontrar o livro pela id
        Livro livro = livroService.encontrarLivroPorId(id);
        //Se o livro não for encontrado, retornar uma excecao
        if(livroService.encontrarLivroPorId(id) == null){
            throw new RuntimeException("Livro não encontrado com o ID: " + id);
        }

        //Se o livro for encontrado, encontrar seus emprestimos associados, categorias associadas,
        // leitores associadas e autor

        List<Leitor> listaLeitores = livro.getListaLeitoresRelacionados();

        List<Emprestimo> listaEmprestimos = livro.getListaEmprestimosRelacionados();

        Autor autorRelacionado = livro.getAutorRelacionado();

        List<Categoria> categoriasRelacionadas = livro.getListaLivrosComCategoria();


        //retornar os valores deste livro, seus autores associados,
        // categorias associadas, emprestimos associados, leitores associados, em DTO

        List<LivroResponse> livroResponseList = Collections.singletonList(new
                LivroResponse(livro.getId(), livro.getTitulo(), livro.getAnoLancamento()));

        AutorResponse autorResponse = new AutorResponse(autorRelacionado.getId(), autorRelacionado.getNome(),
                autorRelacionado.getEmail(), autorRelacionado.getTelefone(), autorRelacionado.getCidade());

        List<EmprestimoResponse> emprestimoResponseList = listaEmprestimos.stream().map(
                emprestimo -> new EmprestimoResponse(emprestimo.getId(),
                        emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao())).toList();

        List<LeitorResponse> leitorResponseList = listaLeitores.stream().map(leitor -> new LeitorResponse
                (leitor.getId(), leitor.getNome(), leitor.getSobrenome(), leitor.getEmail(), leitor.getIdade())).toList();

        List<CategoriaResponse> categoriaResponsesList = categoriasRelacionadas.stream().map(categoria ->
                new CategoriaResponse(categoria.getId(), categoria.getNomeCategoria())).toList();


        //retornar o DTO que vai mostrar o livro e todas as suas relações
        return new LivroAssociationsResponse(livroResponseList,autorResponse,categoriaResponsesList,
                emprestimoResponseList,leitorResponseList);
    }

    @Override
    public List<LivroAssociationsResponse> encontrarTodosLivros() {

        //Encontrar todos os livros
        List<Livro> todosLivros = livroService.encontrarTodosLivros();

        //Criar um array de LivrosAssociations, pra retornar todos os dados dentro do loop for
        List<LivroAssociationsResponse> listLivroAssociationsResponse = new ArrayList<>();


        //Para cada Livro encontrado, retornar suas associações
        for(Livro livroPercorrido: todosLivros) {

            //Obter os dados do livro
            List<LivroResponse> livroResponseList = Collections.singletonList(new
                    LivroResponse(livroPercorrido.getId(), livroPercorrido.getTitulo(), livroPercorrido.getAnoLancamento()));
            //Obter os dados do autor
            Autor autorEncontrado = livroPercorrido.getAutorRelacionado();
                AutorResponse autorResponse = new AutorResponse(autorEncontrado.getId(), autorEncontrado.getNome(),
                        autorEncontrado.getEmail(), autorEncontrado.getTelefone(), autorEncontrado.getCidade());


            //Obter os dados dos Emprestimos
            List<Emprestimo> emprestimosEncontrados = livroPercorrido.getListaEmprestimosRelacionados();
            List<EmprestimoResponse> emprestimoResponse = emprestimosEncontrados.stream().map(emprestimo ->
                    new EmprestimoResponse(emprestimo.getId(), emprestimo.getDataEmprestimo(),
                            emprestimo.getDataDevolucao())).toList();
            //Obter os dados dos Leitores
            List<Leitor> leitoresEncontrados = livroPercorrido.getListaLeitoresRelacionados();
            List<LeitorResponse> leitorResponses = leitoresEncontrados.stream().map(leitor ->
                    new LeitorResponse(leitor.getId(), leitor.getNome(), leitor.getSobrenome(), leitor.getEmail(),
                            leitor.getIdade())).toList();

            //Obter os dados das Categorias
            List<Categoria> categoriasEncontradas = livroPercorrido.getListaLivrosComCategoria();
            List<CategoriaResponse> categoriaResponses = categoriasEncontradas.stream().map(categoria ->
                    new CategoriaResponse(categoria.getId(), categoria.getNomeCategoria())).toList();

            //Salvar tudo no livroAssociationResponse
            LivroAssociationsResponse livroAssociationsResponse = (new LivroAssociationsResponse(livroResponseList,
                    autorResponse, categoriaResponses,
                    emprestimoResponse, leitorResponses));

            //adicionar estes valores, ao array list fora do for, para chamar a resposta no final do método
            listLivroAssociationsResponse.add(livroAssociationsResponse);

        }

        //Retornar todos os livros encontrados com todas as suas associações

        return listLivroAssociationsResponse;
    }

    @Override
    public LivroAssociationsResponse atualizarLivro(Integer id, LivroRequest livrorequest) {

        //Procurar o livro pela id
        Livro livroEncontrado = livroService.encontrarLivroPorId(id);

        //atualizar os valores do livro
        livroService.atualizarLivro(livroEncontrado.getId(), livrorequest);

        //Converter os valores do livro atualizado para DTO... vou pegar os dados do livro, e suas associações

        List<LivroResponse> livroResponse = Collections.singletonList(new LivroResponse(livroEncontrado.getId(), livroEncontrado.getTitulo(),
                livroEncontrado.getAnoLancamento()));

        Autor autorEncontrado = livroEncontrado.getAutorRelacionado();
        AutorResponse autorResponse = (new AutorResponse(autorEncontrado.getId(),
                autorEncontrado.getNome(), autorEncontrado.getEmail(), autorEncontrado.getTelefone(),
                autorEncontrado.getCidade()));

        List<Emprestimo> emprestimosEncontrados = livroEncontrado.getListaEmprestimosRelacionados();
        List<EmprestimoResponse> emprestimosResponseList = emprestimosEncontrados.stream().map(emprestimo ->
                new EmprestimoResponse(emprestimo.getId(),emprestimo.getDataEmprestimo(),
                        emprestimo.getDataDevolucao())).toList();

        List<Categoria> categoriasEncontradas = livroEncontrado.getListaLivrosComCategoria();
        List<CategoriaResponse> categoriaResponsesList = categoriasEncontradas.stream().map
                (categoria -> new CategoriaResponse(categoria.getId(), categoria.getNomeCategoria())).toList();

        List<Leitor> leitoresEncontrados = livroEncontrado.getListaLeitoresRelacionados();
        List<LeitorResponse> leitorResponseList = leitoresEncontrados.stream().map
                (leitor -> new LeitorResponse(leitor.getId(),leitor.getNome(),leitor.getSobrenome(),
                        leitor.getEmail(),leitor.getIdade())).toList();


        //Retornar um novo response DTO ao usuário
        return new LivroAssociationsResponse(livroResponse,autorResponse,categoriaResponsesList,
                emprestimosResponseList,leitorResponseList);
    }

    @Override
    public LivroAssociationsResponse removerLivro(Integer id) {

        //Encontrar o livro pela Id
        Livro livroEncontrado = livroService.encontrarLivroPorId(id);

        //Converter os valores do livro atualizado para DTO... vou pegar os dados do livro, e suas associações
        List<LivroResponse> livroResponse = Collections.singletonList(new LivroResponse(livroEncontrado.getId(), livroEncontrado.getTitulo(),
                livroEncontrado.getAnoLancamento()));

        Autor autorEncontrado = livroEncontrado.getAutorRelacionado();
        AutorResponse autorResponse = (new AutorResponse(autorEncontrado.getId(),
                autorEncontrado.getNome(), autorEncontrado.getEmail(), autorEncontrado.getTelefone(),
                autorEncontrado.getCidade()));

        List<Emprestimo> emprestimosEncontrados = livroEncontrado.getListaEmprestimosRelacionados();
        List<EmprestimoResponse> emprestimosResponseList = emprestimosEncontrados.stream().map(emprestimo ->
                new EmprestimoResponse(emprestimo.getId(),emprestimo.getDataEmprestimo(),
                        emprestimo.getDataDevolucao())).toList();

        List<Categoria> categoriasEncontradas = livroEncontrado.getListaLivrosComCategoria();
        List<CategoriaResponse> categoriaResponsesList = categoriasEncontradas.stream().map
                (categoria -> new CategoriaResponse(categoria.getId(), categoria.getNomeCategoria())).toList();

        List<Leitor> leitoresEncontrados = livroEncontrado.getListaLeitoresRelacionados();
        List<LeitorResponse> leitorResponseList = leitoresEncontrados.stream().map
                (leitor -> new LeitorResponse(leitor.getId(),leitor.getNome(),leitor.getSobrenome(),
                        leitor.getEmail(),leitor.getIdade())).toList();

        //Remover o livro pela sua id, e suas associações
        livroService.deletarLivro(livroEncontrado.getId());


        //Retornar um novo response DTO ao usuário
        return new LivroAssociationsResponse(livroResponse,autorResponse,categoriaResponsesList,
                emprestimosResponseList,leitorResponseList);
    }


}
