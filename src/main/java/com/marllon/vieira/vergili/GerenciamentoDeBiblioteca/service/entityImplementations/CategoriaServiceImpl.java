package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityImplementations;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.CategoriaRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.CategoriaRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeSet;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria criarCategoria(CategoriaRequest categoriaRequest) {

        //Criar nova Categoria
        Categoria novaCategoria = new Categoria();
        novaCategoria.setNomeCategoria(categoriaRequest.nomeCategoria().toUpperCase());

        //Verificar se o usuário digitou os valores certos para criação da categoria
        if(novaCategoria.getNomeCategoria().isBlank() || !novaCategoria.getNomeCategoria().matches("[a-zA-ZÀ-ÿ\\s]+")){
            throw new IllegalArgumentException("Nome categoria não pode ficar em branco, " +
                    "ou conter caracteres especiais ou numeros");
        }

        //Verificação para verificar se essa categoria já não existe
        for(Categoria categoriasPercorridas: categoriaRepository.findAll()){
            if (categoriasPercorridas.getNomeCategoria().contains(novaCategoria.getNomeCategoria())){
                throw new IllegalStateException("Essa categoria ja existe no banco de dados!");

            } else if (categoriasPercorridas.getNomeCategoria().isEmpty()) {
                //Se for a primeira categoria criada, vamos adicioná-la em um nova lista
                Categoria primeiraCategoria = new Categoria();
                primeiraCategoria.setNomeCategoria(categoriaRequest.nomeCategoria());

                //Salvar esse primeiro valor da nova categoria
                categoriaRepository.save(primeiraCategoria);
            }
        }
        //Salvar a categoria
        categoriaRepository.save(novaCategoria);

        //Retornar a categoria
        return novaCategoria;
    }

    @Override
    public Categoria encontrarCategoriaPorId(Integer id) {

        return categoriaRepository.findById(id).orElseThrow(() -> new NoSuchElementException
                ("Nenhuma Categoria foi encontrada com essa id no banco de dados!"));
    }

    @Override
    public TreeSet<Categoria> encontrarTodasCategorias() {
//treeset requer que a classe categoria implemente a interface Comparable, para comparar se tiver com os mesmos nomes
        //Instanciar uma lista de categorias
        List<Categoria> categorias = categoriaRepository.findAll();
        if(categoriaRepository.findAll().isEmpty()){
            throw new NoSuchElementException("Nenhuma Categoria encontrada no Banco de dados!");
        }
        //retornar em uma lista ordenada alfabeticamente(pelo treeset)
        return new TreeSet<>(categorias);

    }

    @Override
    public Categoria encontraCategoriaPorNome(String nome) {
        try{
            return categoriaRepository.findByNome(nome);
        }catch (Exception e){
            throw new NoSuchElementException("Não foi encontrado nenhuma Categoria com esse nome no banco de dados");
        }
    }

    @Override
    public void atualizarCategoria(Integer id, CategoriaRequest categoriaRequest) {


        Categoria categoriaEncontrada = categoriaRepository.findById(id).orElseThrow(() -> new
                NoSuchElementException("Nenhuma categoria encontrada no banco de dados com essa id!"));
        categoriaEncontrada.setNomeCategoria(categoriaRequest.nomeCategoria().toUpperCase());

        //Verificar se os novos dados serão válidos
        if(categoriaEncontrada.getNomeCategoria().isBlank() || !categoriaEncontrada.getNomeCategoria().matches("[a-zA-ZÀ-ÿ\\s]+")){
            throw new IllegalArgumentException("Nome categoria não pode ficar em branco, " +
                    "ou conter caracteres especiais ou numeros");
        }

        //Salvar a categoria
        categoriaRepository.save(categoriaEncontrada);
    }

    @Override
    public void deletarCategoria(Integer id) {

        if (!categoriaRepository.existsById(id)) {
            throw new NoSuchElementException("Nenhuma categoria encontrada com essa ID no banco de dados!");
        }
        categoriaRepository.deleteById(id);
    }
}
