package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationImplementations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.LivroRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroRequestComAutor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.AutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroComAutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LivroRepository;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.AutorAssociationLivroService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.LivroAndAssociationService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.AutorService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.LivroService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LivroAssociationServiceImpl implements LivroAndAssociationService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroService livroService;

    @Autowired
    private AutorService autorService;


    @Override
    @Transactional
    public LivroComAutorResponse criarLivroComAutor(LivroRequestComAutor livroRequestComAutor) {

        //Criar um novo Livro, uando o método de criar livro acima pronto
        Livro novoLivro = livroService.criarLivro(new LivroRequest(livroRequestComAutor.titulo(),
                livroRequestComAutor.anoLancamento()));

        //criar o autor
        Autor novoAutor;
        try{
            novoAutor = autorService.criarAutor(livroRequestComAutor.Autor());
        }catch (RuntimeException e){
            throw new RuntimeException("Erro ao criar o autor!");
        }
        //Associar o livro ao autor
        novoLivro.associarLivroParaUmAutor(novoAutor);

        //salvar o livro com o autor já associado
        livroRepository.save(novoLivro);

        //Converter os dados do autor, para sair ao usuário analisar
        List<LivroResponse> livroResponseList = Collections.singletonList(new LivroResponse(novoLivro.getId(),
                novoLivro.getTitulo(), novoLivro.getAnoLancamento()));

        AutorResponse autorResponse = new AutorResponse(novoAutor.getId(), novoAutor.getNome(),
                novoAutor.getEmail(), novoAutor.getTelefone(), novoAutor.getCidade());

        //retornar a resposta ao usuário do cabeçalho do autor
        return new LivroComAutorResponse(livroResponseList, autorResponse);
    }

    @Override
    public LivroComAutorResponse lerLivroComAutor(Integer id) {

        //Procurar o livro pela id
        Livro livroEncontrado = livroRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum livro encontrado com essa id!"));
        return null;
    }

    @Override
    public List<LivroComAutorResponse> lerTodosLivros() {
        return List.of();
    }



}
