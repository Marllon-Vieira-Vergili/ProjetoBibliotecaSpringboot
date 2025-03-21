package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.dto;
import java.time.Instant;
import java.util.List;

public class EmprestimoDTO {


    private int id;
    private Instant dataEmprestimo;
    private Instant dataDevolucao;
    private boolean estaEmprestado;

    //Relação do Emprestimo com outros DTOs
    private List<LeitorDTO> leitores;
    private List<LivroDTO> livros;

    public EmprestimoDTO(Instant dataEmprestimo, Instant dataDevolucao, boolean estaEmprestado, List<LeitorDTO> leitores, List<LivroDTO> livros) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.estaEmprestado = estaEmprestado;
        this.leitores = leitores;
        this.livros = livros;
    }

    public EmprestimoDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instant getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Instant dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Instant getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Instant dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isEstaEmprestado() {
        return estaEmprestado;
    }

    public void setEstaEmprestado(boolean estaEmprestado) {
        this.estaEmprestado = estaEmprestado;
    }

    public List<LeitorDTO> getLeitores() {
        return leitores;
    }

    public void setLeitores(List<LeitorDTO> leitores) {
        this.leitores = leitores;
    }

    public List<LivroDTO> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroDTO> livros) {
        this.livros = livros;
    }
}
