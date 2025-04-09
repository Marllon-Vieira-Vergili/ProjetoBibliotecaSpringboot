package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.CategoriaRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.CategoriaLivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.CategoriaResponse;
import java.util.List;

public interface CategoriaAssociationService {

    //Criar categoria
    CategoriaResponse criarCategoria(CategoriaRequest categoriaRequest);


    //Ler
    CategoriaLivroResponse encontrarUmaCategoriaELivros(Integer id);
    List<CategoriaLivroResponse> encontrarTodasCategoriasELivrosAssociados();

    //Atualizar
   CategoriaLivroResponse atualizarCategoria(Integer id, CategoriaRequest categoriaRequest);

    //Deletar
    CategoriaLivroResponse removerCategoriaEdesassociarDosLivros(Integer id);


    //Adicionar categoria do livro
    CategoriaLivroResponse adicionarCategoriaAoLivro(Integer categoriaId, Integer livroId);

    //Remover Categoria do livro
    CategoriaLivroResponse removerCategoriaAoLivro(Integer livroId, Integer categoriaId);
}
