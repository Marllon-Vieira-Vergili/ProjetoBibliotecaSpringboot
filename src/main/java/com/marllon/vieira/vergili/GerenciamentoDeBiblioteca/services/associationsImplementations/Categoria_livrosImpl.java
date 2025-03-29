package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.associationsImplementations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.CategoriaELivrosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroECategoriaRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.CategoriaELivrosResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroECategoriaResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.CategoriaRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LivroRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces.Categoria_Livros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class Categoria_livrosImpl implements Categoria_Livros {

    //Instanciando os 2 atributos dos repositórios que leem as entidades

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;


    @Override
    public CategoriaELivrosResponseDTO associarCategoriaALivro(CategoriaELivrosRequestDTO categoriaELivrosRequestDTO) {

        //Encontrando a id da Categoria
        Categoria categoriaEncontrada = categoriaRepository.findById(categoriaELivrosRequestDTO.categoriaId())
                .orElseThrow(() -> new NoSuchElementException("Nenhuma Categoria encontrada com essa id!"));

        //Encontrando a ID(s) dos livros
        List<Livro> listaLivrosEncontrados = livroRepository.findAllById(categoriaELivrosRequestDTO.livroId());

        //Associando a lista de livros a categoria informada
        listaLivrosEncontrados.forEach(livro -> livro.associarLivroACategoria(categoriaEncontrada));

        //Salvando as associações de  categoria aos livros relacionados com a id deles que passamos
        livroRepository.saveAll(listaLivrosEncontrados);

        //Obter os dados dos livros, e converter, mapeando e convertendo para DTO
        List<LivroResponseDTO> livrosDaCategoriaDTO = listaLivrosEncontrados.stream()
                .map(livro -> new LivroResponseDTO(livro.getId(), livro.getNome(),
                        livro.getAnoLancamento())).toList();

        //Retornando ao usuário, uma dto de resposta só com os dados que ele deverá ver
        return new CategoriaELivrosResponseDTO(categoriaEncontrada.getId(),
                categoriaEncontrada.getNomeCategoria(), livrosDaCategoriaDTO);
    }

    @Override
    public List<LivroECategoriaResponseDTO> associarLivroACategoria(LivroECategoriaRequestDTO livroECategoriaRequestDTO) {

        //Encontrando a id dos livros
        List<Livro> livrosEncontrados = livroRepository.findAllById(livroECategoriaRequestDTO.livrosId());

        //Encontrando a id da categoria que será associada
        Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(livroECategoriaRequestDTO.categoriaId());

        //associando os livros a categoria
        livrosEncontrados.forEach(livro -> livro.associarLivroACategoria(categoriaEncontrada.orElseThrow(() ->
                new NoSuchElementException("Nenhuma Categoria foi encontrada!"))));

        //salvando a categoria aos livros
        livroRepository.saveAll(livrosEncontrados);

        //Retornando a resposta do usuário no cabeçalho, para ele ver como foi salvo as assosiações
        return Collections.singletonList(new LivroECategoriaResponseDTO(livrosEncontrados,
                livroECategoriaRequestDTO.categoriaId(), categoriaEncontrada.get().getNomeCategoria()));

    }
}
