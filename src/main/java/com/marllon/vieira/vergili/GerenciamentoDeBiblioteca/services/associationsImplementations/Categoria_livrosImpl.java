package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.associationsImplementations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.CategoriaELivrosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroECategoriaRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.CategoriaResponseDTO;
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
    public LivroECategoriaResponseDTO associarLivroACategoria(LivroECategoriaRequestDTO livroECategoriaRequestDTO) {

        //Encontrar a ID da categoria
        Categoria categoriaEncontrada = categoriaRepository.findById(livroECategoriaRequestDTO.categoriaId())
                .orElseThrow(() -> new NoSuchElementException("Nenhuma categoia encontrada com essa id!"));
        //Encontrar o livro
        List<Livro> livroEncontrado = livroRepository.findAllById(livroECategoriaRequestDTO.livroId());

        //Mapear o livro para converter em DTO
        List<LivroResponseDTO> livroResponseDTO = livroEncontrado.stream()
                .map(livro -> new LivroResponseDTO(livro.getId(),livro.getNome(),
                        livro.getAnoLancamento())).toList();



        //Retornar a resposta DTO para o usuário vizualizar
        return new LivroECategoriaResponseDTO(livroResponseDTO,
                categoriaEncontrada.getId(), categoriaEncontrada.getNomeCategoria());
    }


    @Override
    public List<LivroECategoriaResponseDTO> encontrarCategoriaElivrosAssociados
            (Integer id) {

        //Encontrar a id da categoria
        Categoria categoriaEncontrada = categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nenhuma categoria encontrada com essa id!"));

        //Encontrar a lista de livros, contidos nesta id da categoria
        List<Livro> livrosContidosNaCategoria = categoriaEncontrada.getListaLivrosRelacionados();

        if(categoriaEncontrada.getListaLivrosRelacionados().isEmpty()){
            throw new NoSuchElementException("Não há livros nesta categoria!");
        }


        //Mapear e convertê-lo para DTO response
            List<LivroResponseDTO> livrosResponseDto = livrosContidosNaCategoria.stream().map(livro ->
                    new LivroResponseDTO(livro.getId(), livro.getNome(), livro.getAnoLancamento())).toList();


        //Retornar a lista para o usuário

        return Collections.singletonList((new LivroECategoriaResponseDTO(livrosResponseDto,
                categoriaEncontrada.getId(), categoriaEncontrada.getNomeCategoria())));

    }
}



