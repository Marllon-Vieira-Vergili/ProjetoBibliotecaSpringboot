package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationImplementations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LivroRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation.LivroRequestComAutor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LivroAssociationsResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.AutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.LivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LivroComAutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces.LivroAndAssociationService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.AutorService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.LivroService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LivroAssociationServiceImpl implements LivroAndAssociationService {


    @Autowired
    private LivroService livroService;

    @Autowired
    private AutorService autorService;




    @Override
    @Transactional
    public LivroComAutorResponse criarLivroComAutor(LivroRequestComAutor livroRequestComAutor) {

        //Criar um novo Livro, uando o método de criar livro
        Livro novoLivro = livroService.criarLivro(new LivroRequest(livroRequestComAutor.titulo(),
                livroRequestComAutor.anoLancamento()));

        //criar o autor
        Autor novoAutor;
            novoAutor = autorService.criarAutor(livroRequestComAutor.Autor());

       //associar o livro ao autor
        novoLivro.associarLivroParaUmAutor(novoAutor);

        //Converter os dados do autor, para sair ao usuário analisar
        List<LivroResponse> livroResponseList = Collections.singletonList(new LivroResponse(novoLivro.getId(),
                novoLivro.getTitulo(), novoLivro.getAnoLancamento()));

        AutorResponse autorResponse = new AutorResponse(novoAutor.getId(), novoAutor.getNome(),
                novoAutor.getEmail(), novoAutor.getTelefone(), novoAutor.getCidade());

        //retornar a resposta ao usuário do cabeçalho do autor
        return new LivroComAutorResponse(livroResponseList, (autorResponse));
    }

    @Override
    public LivroAssociationsResponse encontarUmLivro(Integer id) {
        return null;
    }

    @Override
    public List<LivroAssociationsResponse> encontrarTodosLivros() {
        return List.of();
    }


}
