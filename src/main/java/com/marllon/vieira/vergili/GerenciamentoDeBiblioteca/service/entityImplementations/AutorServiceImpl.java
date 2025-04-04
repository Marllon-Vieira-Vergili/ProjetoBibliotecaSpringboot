package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityImplementations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.AutorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
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
                .matches("[a-zA-ZÀ-ÿ\\s]+")) {
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

        //Retornar o autor criado
        return novoAutor;
    }

    @Override
    public Autor encontrarAutorPorId(Integer id) {
        return autorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Não foi encontrado nenhum " +
                "autor com esse ID no banco de dados!"));
    }

    @Override
    public List<Autor> encontrarTodosAutores() {
        if(autorRepository.findAll().isEmpty()){
            throw new NoSuchElementException("Nenhum Autor encontrado no Banco de dados!");
        }
        return autorRepository.findAll();
    }

    @Override
    public List<Autor> encontrarAutorPorNome(String nome) {

        //Encontrar os autores pelo nome
        List<Autor> listaAutor = autorRepository.findByNome(nome);

        //Verificar, quando tiver 2 nomes iguais, ele deverá retornar os 2 resultados, ou mais.
        for(Autor autorPercorrido: listaAutor){
            if(autorPercorrido.getNome().equals(nome)){
                try{
                    return autorRepository.findByNome(nome);
                }catch (Exception e){
                    throw new NoSuchElementException("Não foi encontrado nenhum autor com esse nome no banco de dados!");
                }
            }
        }
        return listaAutor;

    }
    @Override
    @Transactional
    public void atualizarAutor(Integer id, AutorRequest autorRequest) {

        Autor autorEncontrado = autorRepository.findById(id).orElseThrow(() -> new
                NoSuchElementException("Nenhum autor encontrado com essa id no banco de dados!"));
        autorEncontrado.setNome(autorRequest.nome());
        autorEncontrado.setEmail(autorRequest.email());
        autorEncontrado.setTelefone(autorRequest.telefone());
        autorEncontrado.setCidade(autorRequest.cidade());

        //Verificar se os novos dados serão válidos
        if (autorEncontrado.getNome() == null || autorEncontrado.getNome().isEmpty() || autorEncontrado.getNome().isBlank() || !autorEncontrado.getNome()
                .matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("O nome do autor deve conter apenas letras e espaços!");
        }

        if (autorEncontrado.getEmail() == null || autorEncontrado.getEmail().isEmpty() || autorEncontrado.getEmail().isBlank() || !autorEncontrado.getEmail()
                .matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("O email do autor deve conter @!");
        }

        if (autorEncontrado.getTelefone() == null ||autorEncontrado.getTelefone().isEmpty() || autorEncontrado.getTelefone().isBlank() || !autorEncontrado.getTelefone()
                .matches("\\d+")|| autorEncontrado.getTelefone().length() != 11) {
            throw new IllegalArgumentException("O telefone do autor deve conter apenas números, com 11 digitos incluindo DDD padrão Brasileiro!!");
        }

        if (autorEncontrado.getCidade() == null ||autorEncontrado.getCidade().isEmpty() || autorEncontrado.getCidade().isBlank() || !autorEncontrado.getCidade()
                .matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("A cidade do autor deve conter apenas caracteres e espaços!!");
        }

        //Salvar o autor
        autorRepository.save(autorEncontrado);
    }

    @Override
    @Transactional
    public void deletarAutor(Integer id) {
        if (!autorRepository.existsById(id)) {
            throw new NoSuchElementException("Nenhum autor encontrado com este ID no banco de dados!");
        }
        autorRepository.deleteById(id);
    }
}
