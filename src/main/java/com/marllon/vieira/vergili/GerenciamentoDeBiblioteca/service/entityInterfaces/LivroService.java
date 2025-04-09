package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LivroRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import java.util.List;

public interface LivroService {


    //criar
    Livro criarLivro(LivroRequest livroRequest);

    //Ler
    Livro encontrarLivroPorId(Integer id);
    List<Livro> encontrarTodosLivros();
    List<Livro> encontrarLivroPorTitulo(String titulo); //e se eu tiver mais de um livro com o mesmo nome? vai virar lista...

    //Atualizar
    void atualizarLivro(Integer id, LivroRequest livroRequest);

    //Remover
    void deletarLivro(Integer id);


    //Salvar livro
    //Salvar um livro(chamada em outros métodos)
    Livro salvarLivro (Livro livro);
}
