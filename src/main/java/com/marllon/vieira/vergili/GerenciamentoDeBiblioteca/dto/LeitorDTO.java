package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.dto;


import java.util.List;

public class LeitorDTO {


    private String nome;
    private String sobrenome;
    private String email;
    private int idade;

    //Relações do Leitor com outros DTOS
    private List<EmprestimoDTO> emprestimos;
    private List<LivroDTO> livros;

    public LeitorDTO(String nome, String sobrenome, String email, int idade, List<EmprestimoDTO> emprestimos, List<LivroDTO> livros) {

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.idade = idade;
        this.emprestimos = emprestimos;
        this.livros = livros;
    }
    public LeitorDTO(){

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<EmprestimoDTO> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<EmprestimoDTO> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public List<LivroDTO> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroDTO> livros) {
        this.livros = livros;
    }
}
