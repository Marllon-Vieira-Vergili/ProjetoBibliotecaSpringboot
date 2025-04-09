package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.associationInterfaces;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.EmprestimoRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LeitorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.CategoriaLivroResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation.LeitorLivroEmprestimoResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.LeitorResponse;

import java.util.List;

public interface LeitorAssociationService {

    //Criar leitor
    LeitorResponse criarLeitor(LeitorRequest leitorRequest);


    //Ler leitor
    LeitorLivroEmprestimoResponse encontrarUmLeitorEAssociados(Integer leitorId);
    List<LeitorLivroEmprestimoResponse> encontrarVariosLeitoresEAssociados();

    //Atualizar leitor somente
    LeitorLivroEmprestimoResponse atualizarLeitor(Integer leitorId, LeitorRequest leitorRequest);

    //Deletar leitor e associacoes
    LeitorLivroEmprestimoResponse removerLeitorEAssociados(Integer leitorId);


    //Adicionar leitor do livro e leitor do emprestimo
    LeitorLivroEmprestimoResponse adicionarLeitorAoLivro(Integer leitorId, Integer livroId, EmprestimoRequest emprestimoRequest);



}
