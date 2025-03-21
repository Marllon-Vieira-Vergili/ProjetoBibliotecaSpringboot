package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.dto.CategoriaDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.*;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.CategoriaRepositoryImplement;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.LivroRepositoryImplementation;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.CategoriaRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.LivroRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;



/*
SERVICES SÃO A LÒGICA DE NEGÓCIO, A LÒGICA DE NEGÓCIO É O QUE NOS DÁ O VALOR AOS DADOS, E
IMPLEMENTAÇÂO DAS LÒGICAS CORRETAS E TRATADAS EM TODOS OS MÈTODOS

 */


@Service
public class CategoriaService implements Serializable {


    //Instanciando apenas o Repositório de métodos para a Categoria, e Injetando

    @Autowired
    private LivroRepositoryImplementation livroRepositoryImplementation;

    @Autowired
    private CategoriaRepositoryImplement categoriaRepositoryImplement;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    public CategoriaService(CategoriaRepositoryImplement categoriaRepositoryImplement){
        this.categoriaRepositoryImplement = categoriaRepositoryImplement;
    }


    //Método funcionando
    public void saveCategoria(CategoriaDTO categoria) {

        //Verificar se o usuário não irá digitar um espaço em branco na categoria, ou não irá digitá-la,etc

        if(categoria.getNomeCategoria() == null || categoria.getNomeCategoria().isBlank()){
            throw new IllegalArgumentException("Por favor, escreva uma categoria!");

            }
        //verificar se não será passado outro tipo de caracter, a não ser string
        if (!categoria.getNomeCategoria().matches("[a-zA-Z]+")){//se o nome que eu pegar do parametro não condizer com a ordem alfabética de A-Z
            throw new IllegalArgumentException("Categoria só aceita caracteres! e não aceita acentos, nem espaços");
        }


        //Verificar se a categoria já existe no banco
            Optional<Categoria> categoriaExiste = categoriaRepositoryImplement.findCategoriaByNome(categoria.getNomeCategoria());
        if(categoriaExiste.isPresent()){
            throw new IllegalArgumentException("Categoria já existe!");
        }

        //convertendo DTO para entidade e salvando
        Categoria categoriaEntity = modelMapper.map(categoria, Categoria.class);
        categoriaRepositoryImplement.saveCategoria(categoriaEntity);

        //Converter os dados da Categoria Entidade para o CategoriaDTO só mostrar o que necessita no JSON para ser atualizado
        CategoriaDTO categoriaDTO = modelMapper.map(categoriaEntity, CategoriaDTO.class);


        //Vou agora salvar esse categoria nova
        System.out.println("Categoria salva com sucesso!" + categoriaDTO.getNomeCategoria());

    }





    public Optional<Categoria> findCategoriaById(int id) {

        Categoria novaCategoria = categoriaRepositoryImplement.findCategoriaById(id).orElseThrow(() -> new NoSuchElementException("Não há nenhum livro com essa id!"));
        return categoriaRepositoryImplement.findCategoriaById(id);
    }


    public Optional<CategoriaDTO> findCategoriaByNome(String nome){


        TypedQuery<Categoria> query = entityManager.createQuery("SELECT c FROM Categoria c WHERE c.nomeCategoria =:nomeCategoria", Categoria.class);
        query.setParameter("nomeCategoria", nome);

        try{
            //Busca a categoria no banco de dados
            Categoria categoria = query.getSingleResult();

            //Mapeia a categoria para a categoriaDTO
            CategoriaDTO categoriaDTO = modelMapper.map(query, CategoriaDTO.class);

            //Retorna o DTO como optimal
            return Optional.ofNullable(categoriaDTO);
        }catch (NoResultException e){
            //Se nenhum resultado for encontrado, retorna um Optimal vazio
            return Optional.empty();
        }
    }

//Funcionou
    public List<CategoriaDTO> findAllCategorias(){

        List <Categoria> categoriaList = categoriaRepositoryImplement.findAllCategoria();

        if (categoriaList.isEmpty()){
            throw new NoSuchElementException("Não há nenhuma categoria nesta lista!");
        }


    //Convertendo nossa lista de categoria para categoriaDTO, para instanciarmos somente o nome
       List< CategoriaDTO> categoriaDTO = categoriaList.stream().map(categoria -> modelMapper.map(categoria, CategoriaDTO.class)).collect(Collectors.toList());
        return categoriaDTO;
    }




    //Funcionou
    public void updateCategoria(int id, CategoriaDTO categoria){

        //Procurar a categoria já existente no banco de dados, por sua id do pacote de Entidade
        Optional<Categoria> theCategoria = categoriaRepositoryImplement.findCategoriaById(id);


        //Verificar se a categoria está presente vamos atualizá-lo
        if(theCategoria.isPresent()){
            Categoria existingCategoria = theCategoria.get(); //instanciando uma categoria existente, e obtendo os valores da categoria encontrada
            existingCategoria.setNomeCategoria(categoria.getNomeCategoria()); //setar o nome da categoria existente, pelo nome da categoria que passamos de parâmetro
            //existingCategoria.setLivrosCategoria(categoria.getLivrosCategoria());


            //CPegar os dados da minha categoria existente, e transferir para minha categoriaDTO só mostrar o necessário no JSON
           CategoriaDTO categoriaDTO = modelMapper.map(existingCategoria, CategoriaDTO.class);

            categoriaRepositoryImplement.saveCategoria(existingCategoria);

        }else{
            throw new NoSuchElementException("Categoria não encontrado!");
        }

    }



    public void deleteCategoria(int id){

        //tentar encontrar a ID, senão achar,já encaminhar a exceção
        Categoria categoria = categoriaRepositoryImplement.findCategoriaById(id).orElseThrow(()-> new RuntimeException("Livro não encontrado com essa id!"));

        //Se a categoria for encontrado, iremos removê-lo, e desassociá-lo do livro
        if(categoria.getNomeCategoria() != null){
            categoria.getLivrosCategoria().remove(categoria);
        }
        categoria.getLivrosCategoria().clear();

        categoriaRepositoryImplement.deleteCategoria(categoria.getId());
        System.out.println("Categoria com a id: " + id + " deletado com sucesso!");
    }
}



