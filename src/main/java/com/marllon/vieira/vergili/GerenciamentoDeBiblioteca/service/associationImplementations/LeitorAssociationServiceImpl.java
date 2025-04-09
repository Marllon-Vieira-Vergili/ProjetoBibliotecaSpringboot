package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationImplementations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.EmprestimoRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LeitorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.CategoriaLivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LeitorLivroEmprestimoResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.EmprestimoResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.LeitorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.LivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.LeitorAssociationService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.CategoriaService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.EmprestimoService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.LeitorService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.LivroService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LeitorAssociationServiceImpl implements LeitorAssociationService {

    @Autowired
    private LeitorService leitorService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private CategoriaService categoriaService;

    @Override
    public LeitorResponse criarLeitor(LeitorRequest leitorRequest) {

        //Criar o leitor
        Leitor novoLeitor = leitorService.criarLeitor(leitorRequest);

        //converter os dados do leitor para DTO
        LeitorResponse leitorResponse = new LeitorResponse(novoLeitor.getId(), novoLeitor.getNome(),
                novoLeitor.getSobrenome(), novoLeitor.getEmail(), novoLeitor.getIdade());


        //Retornar os dados do leitor para o usuário, em DTO
        return new LeitorResponse(leitorResponse.id(), leitorResponse.nome(), leitorResponse.sobrenome(),
                leitorRequest.email(), leitorResponse.idade());
    }

    @Override
    public LeitorLivroEmprestimoResponse encontrarUmLeitorEAssociados(Integer leitorId) {
        //Encontro o leitor pela id
        Leitor leitorEncontrado = leitorService.encontrarLeitorPorId(leitorId);

        //encontro o livro pela id, e emprestimos relacionados ao leitor


        List<Livro> livrosAssociados = leitorEncontrado.getListaLivrosRelacionadosAoLeitor();
        List<Emprestimo> emprestimosAssociados = leitorEncontrado.getListaEmprestimosRelacionadosAoLeitor();


        //converto os dados
        LeitorResponse leitorResponse = new LeitorResponse(leitorEncontrado.getId(), leitorEncontrado.getNome(),
                leitorEncontrado.getSobrenome(), leitorEncontrado.getEmail(), leitorEncontrado.getIdade());

        List<LivroResponse> livroResponseList = livrosAssociados.stream().map(livro -> new
                LivroResponse(livro.getId(), livro.getTitulo(), livro.getAnoLancamento())).toList();

        List<EmprestimoResponse> emprestimoResponseList = emprestimosAssociados.stream().map(emprestimo ->
                new EmprestimoResponse(emprestimo.getId(), emprestimo.getDataEmprestimo()
                        ,emprestimo.getDataDevolucao())).toList();

        //Retorno ao usuário
        return new  LeitorLivroEmprestimoResponse(leitorResponse, emprestimoResponseList,livroResponseList);
    }

    @Override
    public List<LeitorLivroEmprestimoResponse> encontrarVariosLeitoresEAssociados() {

        //Encontrar todos os leitores
        List<Leitor> todosLeitores = leitorService.encontrarTodosLeitores();


        //Encontrar a lista e os emprestimos

        List<LeitorLivroEmprestimoResponse> leitorLivroEmprestimoResponses = new ArrayList<>();

        for(Leitor leitoresEncontrados: todosLeitores){
            List<Livro> leitoresLivros = leitoresEncontrados.getListaLivrosRelacionadosAoLeitor();
            List<Emprestimo> leitoresEmprestimos = leitoresEncontrados.getListaEmprestimosRelacionadosAoLeitor();

            //converto os dados
            LeitorResponse leitorResponse = new LeitorResponse(leitoresEncontrados.getId(), leitoresEncontrados.getNome(),
                    leitoresEncontrados.getSobrenome(), leitoresEncontrados.getEmail(), leitoresEncontrados.getIdade());

            List<LivroResponse> livroResponseList = leitoresLivros.stream().map(livro -> new
                    LivroResponse(livro.getId(), livro.getTitulo(), livro.getAnoLancamento())).toList();

            List<EmprestimoResponse> emprestimoResponseList = leitoresEmprestimos.stream().map(emprestimo ->
                    new EmprestimoResponse(emprestimo.getId(), emprestimo.getDataEmprestimo()
                            ,emprestimo.getDataDevolucao())).toList();

            leitorLivroEmprestimoResponses.add(new LeitorLivroEmprestimoResponse(leitorResponse, emprestimoResponseList,
                    livroResponseList));
        }

        //Retornar o DTO final ao usuário, com a lista dos leitores, seus livros e emprestimos associados
        return leitorLivroEmprestimoResponses;
    }

    @Override
    public LeitorLivroEmprestimoResponse atualizarLeitor(Integer leitorId, LeitorRequest leitorRequest) {

        //Encontrar o leitor pela id
        Leitor leitorEncontrado = leitorService.encontrarLeitorPorId(leitorId);

        //atualizar os dados do leitor
        leitorService.atualizarLeitor(leitorEncontrado.getId(), leitorRequest);

        //Obter as associacoes do autor e retornar
        List<Livro> leitoresLivros = leitorEncontrado.getListaLivrosRelacionadosAoLeitor();
        List<Emprestimo> leitoresEmprestimos = leitorEncontrado.getListaEmprestimosRelacionadosAoLeitor();

        //converto os dados
        LeitorResponse leitorResponse = new LeitorResponse(leitorEncontrado.getId(), leitorEncontrado.getNome(),
                leitorEncontrado.getSobrenome(), leitorEncontrado.getEmail(), leitorEncontrado.getIdade());

        List<LivroResponse> livroResponseList = leitoresLivros.stream().map(livro -> new
                LivroResponse(livro.getId(), livro.getTitulo(), livro.getAnoLancamento())).toList();

        List<EmprestimoResponse> emprestimoResponseList = leitoresEmprestimos.stream().map(emprestimo ->
                new EmprestimoResponse(emprestimo.getId(), emprestimo.getDataEmprestimo()
                        ,emprestimo.getDataDevolucao())).toList();


        //retornar a resposta
        return new LeitorLivroEmprestimoResponse(leitorResponse,emprestimoResponseList,livroResponseList);
    }


    @Override
    @Transactional
    public LeitorLivroEmprestimoResponse removerLeitorEAssociados(Integer leitorId) {
        Leitor leitorEncontrado = leitorService.encontrarLeitorPorId(leitorId);

        List<Livro> livrosAssociados = leitorEncontrado.getListaLivrosRelacionadosAoLeitor();
        List<Emprestimo> emprestimosAssociados = leitorEncontrado.getListaEmprestimosRelacionadosAoLeitor();

        // Monta os responses antes de limpar
        List<LivroResponse> livroResponseList = livrosAssociados.stream().map(livro ->
                new LivroResponse(livro.getId(), livro.getTitulo(), livro.getAnoLancamento())
        ).toList();

        LeitorResponse leitorResponse = new LeitorResponse(
                leitorEncontrado.getId(),
                leitorEncontrado.getNome(),
                leitorEncontrado.getSobrenome(),
                leitorEncontrado.getEmail(),
                leitorEncontrado.getIdade()
        );

        List<EmprestimoResponse> emprestimoResponseList = emprestimosAssociados.stream().map(emprestimo ->
                new EmprestimoResponse(emprestimo.getId(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao())
        ).toList();

        //Desassociar o leitor dos livros
        for (Livro livro : livrosAssociados) {
            livro.getListaLeitoresRelacionados().remove(leitorEncontrado);
            livroService.salvarLivro(livro);
        }

        //Desassociar e remover os empréstimos relacionados ao leitor
        for (Emprestimo emprestimo : emprestimosAssociados) {

            // Desassociar dos livros
            for (Livro livro : emprestimo.getListaLivrosEmprestados()) {
                livro.getListaEmprestimosRelacionados().remove(emprestimo);
                livroService.salvarLivro(livro);
            }

            // Desassociar do leitor
            emprestimo.getListaLeitoresComEmprestimos().remove(leitorEncontrado);
            emprestimoService.salvarEmprestimo(emprestimo);

            // Deletar o empréstimo
            emprestimoService.deletarEmprestimo(emprestimo.getId());
        }

        // 3. Desassociar listas do leitor
        leitorEncontrado.getListaLivrosRelacionadosAoLeitor().clear();
        leitorEncontrado.getListaEmprestimosRelacionadosAoLeitor().clear();
        leitorService.salvarLeitor(leitorEncontrado);

        // 4. Remover o leitor
        leitorService.deletarLeitor(leitorEncontrado.getId());

        return new LeitorLivroEmprestimoResponse(leitorResponse, emprestimoResponseList, livroResponseList);
    }


    @Override
    public LeitorLivroEmprestimoResponse adicionarLeitorAoLivro(Integer leitorId, Integer livroId,
                                                                EmprestimoRequest emprestimoRequest) {

        //Encontrar leitor desejado
        Leitor leitorEncontrado = leitorService.encontrarLeitorPorId(leitorId);

        //Encontrar livro desejado
        Livro livroEncontrado = livroService.encontrarLivroPorId(livroId);

        //Instanciar um novo emprestimo
        Emprestimo novoEmprestimo = emprestimoService.criarEmprestimo(emprestimoRequest);
        if (novoEmprestimo == null){
            throw new NoSuchElementException("Por favor, criar uma data de emprestimo para associar a esse leitor!");
        }

        //Se o livro for encontrado, associar com o leitor
        leitorEncontrado.associarLeitorALivro(livroEncontrado);
        leitorEncontrado.associarLeitorAEmprestimo(novoEmprestimo);
        livroEncontrado.associarLivroAEmprestimo(novoEmprestimo);
        livroEncontrado.associarLivroALeitor(leitorEncontrado);
        novoEmprestimo.associarEmprestimoALivro(livroEncontrado);
        novoEmprestimo.associarEmprestimoALeitor(leitorEncontrado);

        //Salvar as associacoes bidirecionalmente
        livroService.salvarLivro(livroEncontrado); //Salvar na parte do livro
        leitorService.salvarLeitor(leitorEncontrado); //salvar na parte do leitor
        emprestimoService.salvarEmprestimo(novoEmprestimo); //salvar na parte do emprestimo

        //converto os dados
        LeitorResponse leitorResponse = new LeitorResponse(leitorEncontrado.getId(), leitorEncontrado.getNome(),
                leitorEncontrado.getSobrenome(), leitorEncontrado.getEmail(), leitorEncontrado.getIdade());

        LivroResponse livroResponseList = new LivroResponse(livroEncontrado.getId(), livroEncontrado.getTitulo(),
                livroEncontrado.getAnoLancamento());

        EmprestimoResponse emprestimoResponse = new EmprestimoResponse(novoEmprestimo.getId(), novoEmprestimo
                .getDataEmprestimo(),novoEmprestimo.getDataDevolucao());


        //retornar a resposta
        return new LeitorLivroEmprestimoResponse(leitorResponse,List.of(emprestimoResponse),List.of(livroResponseList));
    }

}
