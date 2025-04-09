package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityImplementations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LivroRequest;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LivroRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.LivroService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LivroServiceImpl implements LivroService {

@Autowired
private LivroRepository livroRepository;

    @Override
    @Transactional
    public Livro criarLivro(LivroRequest livroRequest) {

        //Criar um novo Livro
        Livro novoLivro = new Livro();
        novoLivro.setTitulo(livroRequest.titulo());
        novoLivro.setAnoLancamento(livroRequest.anoLancamento());

        //Verificar se os dados digitados do livro são válidos
        if (livroRequest.titulo() == null || livroRequest.titulo().isEmpty() || !livroRequest.titulo().matches("[A-Za-z0-9\\s]+")) {
            throw new IllegalArgumentException("O título do livro não pode ser nulo, vazio, e só deve conter letras.");
        }
        if (livroRequest.anoLancamento() == null || livroRequest.anoLancamento() < 1900|| livroRequest.anoLancamento() >
                java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("O ano de lançamento do livro deve ser entre 1900 ao ano atual.");
        }

        //Verificação das lógicas.. verificar se este livro criado já não existe
        //Vamos fazer uma condição, se ele encontrar uma id de um livro já criado com as mesmas informações
        for(Livro livroPercorrido: livroRepository.findAll()){
            if(livroPercorrido.getTitulo().equals(livroRequest.titulo())){
                throw new IllegalArgumentException("Este livro já existe!");
            }
            if(livroPercorrido.getId() == null){
                Livro primeiroLivro = new Livro();
                primeiroLivro.setTitulo(livroRequest.titulo());
                primeiroLivro.setAnoLancamento(livroRequest.anoLancamento());
                livroRepository.save(primeiroLivro);
            }
        }
        //Salxar o livro
        livroRepository.save(novoLivro);

        //Retornar o livro
        return novoLivro;
    }


    @Override
    public Livro encontrarLivroPorId(Integer id) {
        return livroRepository.findById(id).orElseThrow(() -> new
                NoSuchElementException("Nenhum livro encontrado com essa id!"));
    }

    @Override
    public List<Livro> encontrarTodosLivros() {
        return livroRepository.findAll();
    }

    @Override
    public List<Livro> encontrarLivroPorTitulo(String titulo) {

        //Encontrar os livros pelo titulo
        List<Livro> listaLivro = livroRepository.findByTitulo(titulo);

        //Verificar, quando tiver 2 nomes iguais, ele deverá retornar os 2 resultados, ou mais.
        for(Livro livroPercorrido: listaLivro){
            if(livroPercorrido.getTitulo().equals(titulo)){
                try{
                    return livroRepository.findByTitulo(titulo);
                }catch (Exception e){
                    throw new NoSuchElementException("Não foi encontrado nenhum livro com esse nome no banco de dados!");
                }
            }
        }
        return listaLivro;
    }

    @Override
    public void atualizarLivro(Integer id, LivroRequest livroRequest) {

        //Procurar o livro pela id
        Livro livroEncontrado = livroRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum livro encontrado com essa id!"));


        //Alterar os dados do livro
        livroEncontrado.setTitulo(livroRequest.titulo());
        livroEncontrado.setAnoLancamento(livroRequest.anoLancamento());

        //Verificar se os dados digitados do livro são válidos
        if (livroRequest.titulo() == null || livroRequest.titulo().isEmpty() || !livroRequest.titulo().matches("[A-Za-z0-9\\s]+")) {
            throw new IllegalArgumentException("O título do livro não pode ser nulo, vazio, e só deve conter letras.");
        }
        if (livroRequest.anoLancamento() == null || livroRequest.anoLancamento() < 1900|| livroRequest.anoLancamento() >
                java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("O ano de lançamento do livro deve ser entre 1900 ao ano atual.");
        }

        //salvar o livro
        livroRepository.save(livroEncontrado);
    }


    @Override
    public void deletarLivro(Integer id) {
        if (!livroRepository.existsById(id)) {
            throw new NoSuchElementException("Nenhum livro encontrado com este ID!");
        }
        livroRepository.deleteById(id);
    }



    //Método salvar livro(chamada em outros metodos)
    public Livro salvarLivro(Livro livro){
        return livroRepository.save(livro);
    }
}
