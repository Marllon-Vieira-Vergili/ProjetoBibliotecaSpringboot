package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.dto;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;

import java.util.List;

public class LivroDTO {


    private String nome;
    private int anoLancamento;

    //Relações do livro com outras entidades DTO
    private AutorDTO autor; //um livro representa apenas um autor
    private List<LeitorDTO> leitores;
    private List<EmprestimoDTO> emprestimos;
    private List<CategoriaDTO> categorias;


    public LivroDTO(String nome, int anoLancamento, AutorDTO autor, List<LeitorDTO> leitores, List<EmprestimoDTO> emprestimos, List<CategoriaDTO> categorias) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.autor = autor;
        this.leitores = leitores;
        this.emprestimos = emprestimos;
        this.categorias = categorias;
    }
    public LivroDTO(){

    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public AutorDTO getAutor() {
        return autor;
    }

    public void setAutor(AutorDTO autor) {
        this.autor = autor;
    }

    public List<LeitorDTO> getLeitores() {
        return leitores;
    }

    public void setLeitores(List<LeitorDTO> leitores) {
        this.leitores = leitores;
    }

    public List<EmprestimoDTO> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<EmprestimoDTO> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }
}