package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.implementations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.CategoriaRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.CategoriaResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.CategoriaRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {


    @Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    public Iterable<CategoriaResponseDTO> listarTodasCategorias() {
        return categoriaRepository.findAll().stream().map(categoria -> new CategoriaResponseDTO
                (categoria.getId(), categoria.getNomeCategoria())).toList();
        /*Retornar e encontrar todos, dividindo por arrays, mapeando uma categoria para uma nova resposta
            através do record CategoriaResponseDTO, na qual vai pegar a id, nome e lista de livros relacionados, e devolver
            em uma lista, e depois converter para uma lista de categoriaResponseDTO
         */
    }


    @Override
    public CategoriaResponseDTO encontrarCategoriaPorId(Integer id) {

        Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(id);

        return categoriaEncontrada.map(categoria -> new CategoriaResponseDTO(categoria.getId(),
                categoria.getNomeCategoria())).orElse(null);
    }

    @Override
    public CategoriaResponseDTO salvarCategoria(CategoriaRequestDTO categoria) {

        //Instanciando uma nova categoria
        Categoria novaCategoria = new Categoria(categoria.nomeCategoria());
        //salvar a nova categoria
        categoriaRepository.save(novaCategoria);

        //Retornar ao usuário, a nova categoria salva
        return new CategoriaResponseDTO(novaCategoria.getId(),
                novaCategoria.getNomeCategoria());
    }

    @Override
    public CategoriaResponseDTO atualizarCategoria(Integer id, CategoriaRequestDTO categoriaDTO) {

        //Localizando a entidade da Categoria, pela instancia da categoria, procurando pela sua ID no banco de dados
        Categoria categoriaEncontrada = categoriaRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Categoria não encontrada!"));

//Se achou a id da categoria, então estamos atualizando os dados da categoria, conforme passado do parâmetro da CategoriaDTO
            categoriaEncontrada.setNomeCategoria(categoriaDTO.nomeCategoria());

        //Salvar os dados atualizados no banco de dados
        categoriaRepository.save(categoriaEncontrada);

        return new CategoriaResponseDTO(categoriaEncontrada.getId(), categoriaEncontrada.getNomeCategoria());
    }

    @Override
    public void deletarCategoria(Integer id) {

        Categoria categoriaEncontrada = categoriaRepository.findById(id).orElseThrow(() -> new
                NoSuchElementException("Nenhuma Categoria encontrada com essa id!"));

        categoriaRepository.delete(categoriaEncontrada);
    }
}
