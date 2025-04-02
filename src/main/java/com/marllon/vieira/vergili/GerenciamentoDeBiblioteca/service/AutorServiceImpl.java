package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.LivroRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.AutorComLivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroComAutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl  implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroService livroService;


    @Override
    public void criarAutor(AutorRequest autorRequest) {

        //Criar novo autor
        Autor novoAutor = new Autor();
        novoAutor.setNome(autorRequest.nome());
        novoAutor.setEmail(autorRequest.email());
        novoAutor.setTelefone(autorRequest.telefone());
        novoAutor.setCidade(autorRequest.cidade());

        //Verificar se o usuário digitou os valores certos para o autor
        if (novoAutor.getNome().isEmpty() || novoAutor.getNome().isBlank() || !novoAutor.getNome()
                .matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("O nome do autor deve conter apenas letras e espaços!");
        }

        if (novoAutor.getEmail().isEmpty() || novoAutor.getEmail().isBlank() || !novoAutor.getEmail()
                .matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("O email do autor deve conter @!");
        }

        if (novoAutor.getTelefone().isEmpty() || novoAutor.getTelefone().isBlank() || !novoAutor.getTelefone()
                .matches("\\d+")|| novoAutor.getTelefone().length() != 11) {
            throw new IllegalArgumentException("O telefone do autor deve conter apenas números, com 11 digitos incluindo DDD padrão Brasileiro!!");
        }

        if (novoAutor.getCidade().isEmpty() || novoAutor.getCidade().isBlank() || !novoAutor.getCidade()
                .matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("A cidade do autor deve conter apenas caracteres e espaços!!");
        }

        //Verificação das lógicas.. verificar se estes autores criados, já existe

        //Vamos fazer uma condição, se ele encontrar uma id de autor

        for (Autor percorrerAutores : autorRepository.findAll()) {
            if (percorrerAutores.getId() == null) {
                Autor primeiroAutor = new Autor();
                primeiroAutor.setNome(autorRequest.nome());
                primeiroAutor.setEmail(autorRequest.email());
                primeiroAutor.setTelefone(autorRequest.telefone());
                primeiroAutor.setCidade(autorRequest.cidade());
                autorRepository.save(primeiroAutor);
            }
                Optional<Autor> autorExiste = autorRepository.findById(percorrerAutores.getId());
                if (autorExiste.isPresent()) {
                    if (autorExiste.get().getEmail().equals(novoAutor.getEmail())) { //Se o email dos 2 forem iguais
                        throw new IllegalArgumentException("Não é possível criar um autor com um email já existente!");
                    }
                }
            }
            //Salvar o autor
            autorRepository.save(novoAutor);
    }



    @Override
    public AutorComLivroResponse criarAutorComLivro(AutorRequest autorRequest, LivroRequest livroRequest) {

        //Criar um novo autor, chamando o método criarAutor
        criarAutor(autorRequest);

        //criar um livro
        Livro novoLivro = new Livro();
        novoLivro.setTitulo(livroRequest.titulo());
        novoLivro.setAnoLancamento(livroRequest.anoLancamento());

        //associar o autor ao livro



        //retornar o autor já criado com o livro associado
        return null;
    }

}







/*
    @Override
    public AutorComLivroResponse criarAutorComLivro(AutorRequest autorRequest) {

        //Criar novo autor
        Autor novoAutor = new Autor();
        novoAutor.setNome(autorRequest.nome());
        novoAutor.setEmail(autorRequest.email());
        novoAutor.setTelefone(autorRequest.telefone());
        novoAutor.setCidade(autorRequest.cidade());

        //Verificar se o usuário digitou os valores certos para o autor
        if(novoAutor.getNome().isEmpty() || novoAutor.getNome().isBlank() || !novoAutor.getNome()
                .matches("[a-zA-Z\\s]+")){
            throw new IllegalArgumentException("O nome do autor deve conter apenas letras e espaços!");
        }

        if(novoAutor.getEmail().isEmpty() || novoAutor.getEmail().isBlank() || !novoAutor.getEmail()
                .matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
            throw new IllegalArgumentException("O email do autor deve conter @!");
        }

        if(novoAutor.getTelefone().isEmpty()|| novoAutor.getTelefone().isBlank() || !novoAutor.getTelefone()
                .matches("\\d+")){
            throw new IllegalArgumentException("O telefone do autor deve conter apenas números!!");
        }

        if(novoAutor.getCidade().isEmpty()|| novoAutor.getCidade().isBlank() || !novoAutor.getCidade()
                .matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("A cidade do autor deve conter apenas caracteres e espaços!!");
        }

        //Verificação das lógicas.. verificar se estes autores criados, já existe
        Optional<Autor> autorExiste = autorRepository.findById(novoAutor.getId());

        if(autorExiste.isPresent()){
            if (autorExiste.get().getEmail().equals(novoAutor.getEmail())){ //Se o email dos 2 forem iguais
                throw new IllegalArgumentException("Não é possível criar um autor com um email já existente!");
            }
        }


        //Associar um livro ao autor
        List<Livro> livroEncontrado = livroRepository.findAllById(autorRequest.livroIds());

        //Se o livro que eu quero adicionar, já existir
        if (livroEncontrado.equals(autorRequest.livroIds())) {
            throw new IllegalArgumentException("O livro já está associado a este autor!");

        }else if(livroEncontrado.isEmpty()){
                //Criar uma nova lista de arrayList para esse autor, de Livros
                List<Livro> novaListaLivros = new ArrayList<>();
                novaListaLivros.add((Livro) livroEncontrado);
            }

            //Associar a id de um livro ao autor

            novoAutor.associarAutorParaUmLivro((Livro) livroEncontrado); //Associar um objeto livro da lista para livro encontrado


        //Salvar o novo autor com o livro já associado
        try{
            autorRepository.save(novoAutor);
        }catch (RuntimeException e){
            throw new RuntimeException("Erro ao salvar o autor!");
        }


        //Converter os dados do livroResponse para sair
        LivroComAutorResponse livroResponseDTO = new LivroComAutorResponse(((Livro) livroEncontrado).getId(),
                ((Livro) livroEncontrado).getTitulo(),((Livro) livroEncontrado).getAnoLancamento());

        //Retornar o autor criado ao usuário pela record AutorRequest DTO
        return new AutorComLivroResponse(novoAutor.getId(), novoAutor.getNome(), novoAutor.getCidade(),
                Collections.singletonList(livroResponseDTO));
    }
 */


























//LÒGICA SOMENTE PARA MEXER NO AUTOR, FUNCIONANDO __ BACKUP
/*
    @Override
    public AutorComLivroResponse criarAutorComLivro(AutorRequest autorRequest) {

        //Criar novo autor
        Autor novoAutor = new Autor();
        novoAutor.setNome(autorRequest.nome());
        novoAutor.setEmail(autorRequest.email());
        novoAutor.setTelefone(autorRequest.telefone());
        novoAutor.setCidade(autorRequest.cidade());

        //Verificar se o usuário digitou os valores certos para o autor
        if(novoAutor.getNome().isEmpty() || novoAutor.getNome().isBlank() || !novoAutor.getNome()
                .matches("[a-zA-Z\\s]+")){
            throw new IllegalArgumentException("O nome do autor deve conter apenas letras e espaços!");
        }

        if(novoAutor.getEmail().isEmpty() || novoAutor.getEmail().isBlank() || !novoAutor.getEmail()
                .matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
            throw new IllegalArgumentException("O email do autor deve conter @!");
        }

        if(novoAutor.getTelefone().isEmpty()|| novoAutor.getTelefone().isBlank() || !novoAutor.getTelefone()
                .matches("\\d+")){
            throw new IllegalArgumentException("O telefone do autor deve conter apenas números!!");
        }

        if(novoAutor.getCidade().isEmpty()|| novoAutor.getCidade().isBlank() || !novoAutor.getCidade()
                .matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("A cidade do autor deve conter apenas caracteres e espaços!!");
        }

        //Verificação das lógicas.. verificar se estes autores criados, já existe

        //Vamos fazer uma condição, se ele encontrar uma id de autor

        for (Autor percorrerAutores: autorRepository.findAll()){
            if(percorrerAutores.getId() == null){
                Autor primeiroAutor = new Autor();
                primeiroAutor.setNome(autorRequest.nome());
                primeiroAutor.setEmail(autorRequest.email());
                primeiroAutor.setTelefone(autorRequest.telefone());
                primeiroAutor.setCidade(autorRequest.cidade());
                return new AutorComLivroResponse(primeiroAutor.getId(), primeiroAutor.getNome(), primeiroAutor.getCidade());
            }
            Optional<Autor> autorExiste = autorRepository.findById(percorrerAutores.getId());
            if(autorExiste.isPresent()){
                if (autorExiste.get().getEmail().equals(novoAutor.getEmail())){ //Se o email dos 2 forem iguais
                    throw new IllegalArgumentException("Não é possível criar um autor com um email já existente!");
                }
            }
        }

        //Salvar o autor
        autorRepository.save(novoAutor);


        //Retornar o autor criado ao usuário pela record AutorRequest DTO
        return new AutorComLivroResponse(novoAutor.getId(), novoAutor.getNome(), novoAutor.getCidade());
    }

}

 */