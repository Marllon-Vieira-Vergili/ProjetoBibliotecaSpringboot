package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.LivroRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroComAutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LivroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroServiceImpl implements LivroService{

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public void criarLivro(LivroRequest livroRequest) {

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
    }

    @Override
    public LivroComAutorResponse criarLivroComAutor(LivroRequest livroRequest) {

        criarLivro(livroRequest);

        //Associar o livro ao autor

        return null;
    }





    //Método customizado para encontrar o livro pelo Título
   public Livro encontrarPeloTitulo(LivroRequest livroRequest){
        return livroRepository.findByTitulo(livroRequest.titulo());
   }
}
