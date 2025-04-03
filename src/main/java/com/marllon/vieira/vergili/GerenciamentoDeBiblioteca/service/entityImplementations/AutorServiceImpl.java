package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityImplementations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.AutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.AutorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AutorServiceImpl implements AutorService {

@Autowired
private AutorRepository autorRepository;

    //Lógica funcionando ok, para criar os livros
    @Override
    @Transactional
    public Autor criarAutor(AutorRequest autorRequest) {

        //Criar novo autor
        Autor novoAutor = new Autor();
        novoAutor.setNome(autorRequest.nome());
        novoAutor.setEmail(autorRequest.email());
        novoAutor.setTelefone(autorRequest.telefone());
        novoAutor.setCidade(autorRequest.cidade());

        //Verificar se o usuário digitou os valores certos para o autor
        if (novoAutor.getNome() == null || novoAutor.getNome().isEmpty() || novoAutor.getNome().isBlank() || !novoAutor.getNome()
                .matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("O nome do autor deve conter apenas letras e espaços!");
        }

        if (novoAutor.getEmail() == null || novoAutor.getEmail().isEmpty() || novoAutor.getEmail().isBlank() || !novoAutor.getEmail()
                .matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("O email do autor deve conter @!");
        }

        if (novoAutor.getTelefone() == null ||novoAutor.getTelefone().isEmpty() || novoAutor.getTelefone().isBlank() || !novoAutor.getTelefone()
                .matches("\\d+")|| novoAutor.getTelefone().length() != 11) {
            throw new IllegalArgumentException("O telefone do autor deve conter apenas números, com 11 digitos incluindo DDD padrão Brasileiro!!");
        }

        if (novoAutor.getCidade() == null ||novoAutor.getCidade().isEmpty() || novoAutor.getCidade().isBlank() || !novoAutor.getCidade()
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

        //Retornar os dados do novo autor
        return novoAutor;
    }
}
