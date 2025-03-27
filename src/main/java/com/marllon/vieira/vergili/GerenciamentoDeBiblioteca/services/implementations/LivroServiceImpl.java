package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.implementations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.LivroRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LivroRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LivroServiceImpl implements LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public Iterable<LivroResponseDTO> listarTodosLivros() {
        return livroRepository.findAll().stream().map(livro -> new LivroResponseDTO(livro.getId(),
                livro.getNome(), livro.getAnoLancamento(),livro.getAutorRelacionado(),
                livro.getListaLeitoresRelacionados(),livro.getListaEmprestimosRelacionados(),
                livro.getListaLivrosComCategoria())).toList();
    }

    @Override
    public LivroResponseDTO encontrarLivroPorId(Integer id) {
        Livro livroencontrado = livroRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum livro encontrado com essa id!"));
        return new LivroResponseDTO(livroencontrado.getId(),
                livroencontrado.getNome(),livroencontrado.getAnoLancamento(), livroencontrado.getAutorRelacionado(),
                livroencontrado.getListaLeitoresRelacionados(),livroencontrado.getListaEmprestimosRelacionados(),
                livroencontrado.getListaLivrosComCategoria());
    }

    @Override
    public LivroResponseDTO salvarLivro(LivroRequestDTO livroRequestDTO) {

        //Instanciar um novo livro
        Livro novoLivro = new Livro(livroRequestDTO.nome(), livroRequestDTO.anoLancamento());

        //Salvar um novo livro
        livroRepository.save(novoLivro);

        //Converter os dados do livro para retornoemDTO para o usuário vizualizar

        return new LivroResponseDTO(novoLivro.getId(), novoLivro.getNome(), novoLivro.getAnoLancamento(),
                novoLivro.getAutorRelacionado(), novoLivro.getListaLeitoresRelacionados(),
                novoLivro.getListaEmprestimosRelacionados(), novoLivro.getListaLivrosComCategoria());
    }

    @Override
    public LivroResponseDTO atualizarLivro(Integer id, LivroRequestDTO livroRequestDTO) {

        //Encontrar o livro pela sua ID
        Livro livroEncontrado = livroRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum livro encontrado com essa id!"));

        //Alterar os valores do livro
        livroEncontrado.setNome(livroRequestDTO.nome());
        livroEncontrado.setAnoLancamento(livroRequestDTO.anoLancamento());

//Salvar o livro
        livroRepository.save(livroEncontrado);

        //Retornar o novo Livro em mensagem para o DTO
        return new LivroResponseDTO(livroEncontrado.getId(), livroEncontrado.getNome(),
                livroEncontrado.getAnoLancamento(), livroEncontrado.getAutorRelacionado(),
                livroEncontrado.getListaLeitoresRelacionados(),livroEncontrado.getListaEmprestimosRelacionados(),
                livroEncontrado.getListaLivrosComCategoria());
    }


    @Override
    public void deletarLivro(Integer id) {

        //Encontrar o livro pela sua ID
        Livro livroSerDeletado = livroRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum livro encontrado com essa id!"));

        //Remover o livro encontrado pela id
        livroRepository.delete(livroSerDeletado);
    }
}
