package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.associationsImplementations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.EmprestimoELivrosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivrocomEmprestimosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.EmprestimoResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.EmprestimoELivrosResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroComEmprestimoResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.EmprestimoRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LivroRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces.Emprestimo_Livros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class Emprestimos_livrosImpl implements Emprestimo_Livros {


    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public EmprestimoELivrosResponseDTO associarEmprestimoALivros(EmprestimoELivrosRequestDTO emprestimoELivrosRequestDTO) {

        //Encontrar o emprestimo
        Emprestimo emprestimoEncontrado = emprestimoRepository.findById(emprestimoELivrosRequestDTO.emprestimoId()).orElseThrow(() ->
                new NoSuchElementException("Nenhum emprestimo encontrado com essa id!"));

        //encontrar o livro(s)
        Livro livroEncontrado = livroRepository.findById(emprestimoELivrosRequestDTO.livroId()).orElseThrow(() ->
                new NoSuchElementException("Nenhum emprestimo encontrado com essa id!"));

        //Associar o emprestimo ao livro
        emprestimoEncontrado.associarEmprestimoALivro(livroEncontrado);

        //Salvar o emprestimo
        emprestimoRepository.save(emprestimoEncontrado);

        //converter em formato de saida dto
        List<LivroResponseDTO> livroResponseDTOS = Stream.of(livroEncontrado).
                map(livro -> new LivroResponseDTO(livro.getId(),
                        livro.getNome(), livro.getAnoLancamento())).toList();

        //Retornar o DTO ao usuário

        return new EmprestimoELivrosResponseDTO(emprestimoEncontrado.getId(), emprestimoEncontrado.getDataEmprestimo()
                ,emprestimoEncontrado.getDataDevolucao(), emprestimoEncontrado.isEstaEmprestado(), livroResponseDTOS);
    }

    @Override
    public LivroComEmprestimoResponseDTO associarLivrosAEmprestimo
            (LivrocomEmprestimosRequestDTO livrocomEmprestimosRequestDTO) {

        //Encontrar o emprestimo
        Emprestimo emprestimoEncontrado = emprestimoRepository.findById(livrocomEmprestimosRequestDTO.emprestimoId()).orElseThrow(() ->
                new NoSuchElementException("Nenhum emprestimo encontrado com essa id!"));

        //encontrar o livro(s)
        Livro livroEncontrado = livroRepository.findById(livrocomEmprestimosRequestDTO.LivroId()).orElseThrow(() ->
                new NoSuchElementException("Nenhum emprestimo encontrado com essa id!"));

        //Associar o livro ao empréstimo
        livroEncontrado.associarLivroAEmprestimo(emprestimoEncontrado);

        //Salvar o livro
        livroRepository.save(livroEncontrado);

        //Converter a entidade para DTO
        List<LivroResponseDTO> livroResponseDTOS = Stream.of(new LivroResponseDTO(livroEncontrado.getId(),
                livroEncontrado.getNome(), livroEncontrado.getAnoLancamento())).toList();

        List<EmprestimoResponseDTO> emprestimoResponseDTOS = List.of(new EmprestimoResponseDTO
                (emprestimoEncontrado.getId(), emprestimoEncontrado.getDataEmprestimo(),
                        emprestimoEncontrado.getDataDevolucao(),emprestimoEncontrado.isEstaEmprestado()));

        //Retornar o DTO ao usuário
        return new LivroComEmprestimoResponseDTO(livroResponseDTOS, emprestimoResponseDTOS);
    }

    @Override
    public LivroComEmprestimoResponseDTO encontrarLivroComEmprestimo(Integer id) {

        //Encontrar o emprestimo
        Emprestimo emprestimoEncontrado = emprestimoRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum emprestimo encontrado com essa id!"));

        //encontrar o livro(s)
        Livro livroEncontrado = livroRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum emprestimo encontrado com essa id!"));


        //Converter em formato DTO
        List<LivroResponseDTO> livroResponseDTOS = Collections.singletonList(new LivroResponseDTO(livroEncontrado.getId(),
                livroEncontrado.getNome(), livroEncontrado.getAnoLancamento()));

        List<EmprestimoResponseDTO> emprestimoResponseDTOS = List.of(new EmprestimoResponseDTO
                (emprestimoEncontrado.getId(), emprestimoEncontrado.getDataEmprestimo(),
                        emprestimoEncontrado.getDataDevolucao(),emprestimoEncontrado.isEstaEmprestado()));


        //Retornar o valor ao usuário
        return new LivroComEmprestimoResponseDTO(livroResponseDTOS, emprestimoResponseDTOS);
    }
}
