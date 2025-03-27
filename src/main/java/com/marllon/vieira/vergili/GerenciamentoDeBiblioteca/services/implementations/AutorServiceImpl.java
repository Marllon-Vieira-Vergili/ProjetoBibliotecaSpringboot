package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.implementations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.AutorRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.AutorResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;


    @Override
    public Iterable<AutorResponseDTO> listarTodosOsAutores() {


        return autorRepository.findAll().stream().map(autores -> new AutorResponseDTO(autores.getId(),
                autores.getNome(), autores.getEmail(), autores.getTelefone(), autores.getCidade(),
                autores.getListaLivrosDosAutores())).collect(Collectors.toList());
    }

    @Override
    public Optional<AutorResponseDTO> listarAutorPorId(Integer id) {

        //Pegar dados do autor pela Entidade Autor
        Optional<Autor> autorEncontradoPelaId = autorRepository.findById(id);

        //Converter os dados encontrados para retornar em uma DTO para o usuário vizualizar, que é o retorno do método
        return autorEncontradoPelaId.map(autor -> Optional.of(new AutorResponseDTO(autor.getId(),
                autor.getNome(), autor.getEmail(),
                autor.getTelefone(), autor.getCidade(),
                autor.getListaLivrosDosAutores()))).orElse(null);
    }

    @Override
    public AutorResponseDTO salvarAutor(AutorRequestDTO autor) {
        //Instanciar um novo autor, para pegar os dados da entidade, pra salvar no banco de dados
        Autor autorNovo = new Autor(autor.nome(), autor.email(), autor.telefone(), autor.cidade());
        //Salvar um novo autor no banco de dados
        autorRepository.save(autorNovo);
        //converter os dados do autor para retornar na resposta do DTO para o usuário vizualizar
        return new AutorResponseDTO(
               autorNovo.getId(),
                autorNovo.getNome(),
                autorNovo.getEmail(),
                autorNovo.getTelefone(),
                autorNovo.getCidade(), autorNovo.getListaLivrosDosAutores());
    }

    @Override
    public AutorResponseDTO atualizarAutor(Integer id, AutorRequestDTO autorDTO) {

        //Localizando a entidade do autor, pela instancia do autor, procurando pela sua ID no banco de dados
Autor autorLocalizado = (autorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id do autor não encontrado!")));

//Se achou a id do autor, então estamos atualizando os dados do autor, conforme passado do parâmetro do AutorDTO

        autorLocalizado.setNome(autorDTO.nome());
        autorLocalizado.setEmail(autorDTO.email());
        autorLocalizado.setTelefone(autorDTO.telefone());
        autorLocalizado.setCidade(autorDTO.cidade());

        //Salvar os dados atualizados no banco de dados
       autorRepository.save(autorLocalizado);

       return new AutorResponseDTO(autorLocalizado.getId(), autorLocalizado.getNome(),
               autorLocalizado.getEmail(), autorDTO.telefone(), autorLocalizado.getCidade(),
               autorLocalizado.getListaLivrosDosAutores());
    }



    @Override
    public void deletarAutor(Integer id) {

        Autor autorEncontrado = autorRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Nenhum autor encontrado!"));
        //Deletar o autor
        autorRepository.delete(autorEncontrado);

    }
}
