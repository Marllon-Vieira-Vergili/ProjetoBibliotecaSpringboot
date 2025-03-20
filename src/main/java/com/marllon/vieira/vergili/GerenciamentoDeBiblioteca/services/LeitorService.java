package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.*;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.LeitorRepositoryImplementation;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repository.LeitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;



/*
SERVICES SÃO A LÒGICA DE NEGÓCIO, A LÒGICA DE NEGÓCIO É O QUE NOS DÁ O VALOR AOS DADOS, E
IMPLEMENTAÇÂO DAS LÒGICAS CORRETAS E TRATADAS EM TODOS OS MÈTODOS

 */


@Service
public class LeitorService implements Serializable {


    //Instanciando apenas o Repositório de métodos para o Livro, e Injetando

    @Autowired
    private LeitorRepositoryImplementation leitorRepositoryImplementation;


    public LeitorService(LeitorRepositoryImplementation leitorRepositoryImplementation) {
        this.leitorRepositoryImplementation = leitorRepositoryImplementation;
    }

    public Optional<Leitor> encontrarLeitorPeloNome(String nome) {

        try {
            return leitorRepositoryImplementation.encontrarLeitorPeloNome(nome);

        } catch (NoSuchElementException e) {
            return null;
        }

    }

    public void saveLeitor(Leitor leitor) {

        //Verificar se os dados passados para a criação do leitor não serão nulos, ou se já existe,
        //Quando instanciarmos em outro nosso método..

        if(leitor == null){
            throw new IllegalArgumentException("O leitor não pode ser nulo!");
        }
        if(leitor.getNome() == null || leitor.getNome().isEmpty()){
            throw new IllegalArgumentException("O nome do leitor não pode ser nulo!");
        }
        if (leitor.getSobrenome() == null || leitor.getSobrenome().isEmpty()){
            throw new IllegalArgumentException("O sobrenome do leitor é obrigatório!");
        }
        if (leitor.getIdade() >= 0 && leitor.getIdade() < 12){
            throw new IllegalArgumentException("A idade do leitor é obrigatório, e não pode ser menor que 12 anos!");
        }
        if (leitor.getEmail() == null || leitor.getEmail().isEmpty()){
            throw new IllegalArgumentException("O Email do leitor é obrigatório!");
        }


        //Verificar se ja existirá um leitor com os mesmos dados, através de seu email
        Optional<Leitor> leitorExiste = leitorRepositoryImplementation.findLeitorByEmail(leitor.getEmail());

        if (leitorExiste.isPresent()) {
            throw new IllegalStateException("Já existe um autor com esse e-mail cadastrado: " + leitor.getEmail());
        }

        //Instanciar um novo autor
        Leitor novoLeitor = new Leitor();
        novoLeitor.setNome(leitor.getNome());
        novoLeitor.setEmail(leitor.getEmail());
        novoLeitor.setIdade(leitor.getIdade());
        novoLeitor.setSobrenome(leitor.getSobrenome());

        //salvar o autor no banco de dados
        try{
            leitorRepositoryImplementation.saveLeitor(novoLeitor);
            System.out.println("Leitor cadastrado com sucesso! Seguem os novos dados do leitor adicionado:  " + novoLeitor);
        } catch (Exception e){
            throw new IllegalStateException("Erro ao salvar o leitor!" + e.getMessage());
        }
    }





    public Optional<Leitor> findLeitorById(int id) {

        Leitor leitor = leitorRepositoryImplementation.findLeitorById(id).orElseThrow(() -> new  NoSuchElementException ("Não há nenhum leitor com essa id!"));

        return leitorRepositoryImplementation.findLeitorById(id);
    }



    public List<Leitor> findAllLeitor(){

        try{
            return leitorRepositoryImplementation.findAllLeitor();
        }catch (NoSuchElementException e){
            return null;
        }
    }


    public void updateLeitor(int id, Leitor leitor){

        //Procurar o livro já existente no banco de dados, por sua id
        Optional<Leitor> theLeitor = leitorRepositoryImplementation.findLeitorById(id);


        //Verificar se o leitor está presente vamos atualizá-lo, sem mexer resto
        if(theLeitor.isPresent()){
            Leitor existingLeitor = theLeitor.get(); //instanciando um leitorExistente, e obtendo os valores do leitor encontrado
            existingLeitor.setNome(leitor.getNome()); //setar o nome do leitor existente, pelo nome do leitor que passamos de parâmetro
            existingLeitor.setSobrenome(leitor.getSobrenome());
            existingLeitor.setIdade(leitor.getIdade());
            existingLeitor.setEmail(leitor.getEmail());
            leitorRepositoryImplementation.saveLeitor(existingLeitor);
        }else{
            throw new NoSuchElementException("leitor não encontrado!");
        }

    }



    public void deleteLeitor(int id){

        //tentar encontrar a ID, senão achar,já encaminhar a exceção
        Leitor leitor = leitorRepositoryImplementation.findLeitorById(id).orElseThrow(()-> new RuntimeException("Livro não encontrado com essa id!"));


        //Desassociar o leitor manualmente dos livros, e dos empréstimos
        //nos Cascades

        if(leitor.getLivro() != null){
            leitor.getLivro().remove(leitor);
            leitor.setLivro(null);
        }

        if(leitor.getLeitorEmprestimos() != null) {
            for (Emprestimo emprestimo : leitor.getLeitorEmprestimos()) {
                emprestimo.getLivros().remove(emprestimo);

            }
            leitor.getLeitorEmprestimos().clear();
        }

        //Se o leitor for encontrado, iremos removê-lo, e desassociá-lo livro e do empréstimo

        leitorRepositoryImplementation.deleteLeitor(leitor.getId());
        System.out.println("Leitor com a id: " + id + " deletado com sucesso!");
    }



    public Optional<Leitor> findLeitorByEmail(String email){

        try{

            return leitorRepositoryImplementation.findLeitorByEmail(email);

        }catch (NoSuchElementException e){
            return null;
        }

    }
}



