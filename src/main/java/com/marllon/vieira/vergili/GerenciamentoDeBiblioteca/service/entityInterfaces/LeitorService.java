package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LeitorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import java.util.List;

public interface LeitorService {


    //Interface CRUD somente para as entidades


    //Criar
    Leitor criarLeitor(LeitorRequest leitorRequest);//associação bidirecional

    //Ler
    Leitor encontrarLeitorPorId(Integer id);
    List<Leitor> encontrarTodosLeitores();
    List<Leitor> encontrarLeitorPorNome(String nome); //e se eu tiver mais de um leitor com o mesmo nome? vai virar lista...

    //Atualizar
    void atualizarLeitor(Integer id, LeitorRequest leitorRequest);

    //Remover
    void deletarLeitor(Integer id);
}
