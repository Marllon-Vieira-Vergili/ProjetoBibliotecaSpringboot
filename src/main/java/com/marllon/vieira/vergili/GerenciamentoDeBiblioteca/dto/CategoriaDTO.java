package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.dto;



public class CategoriaDTO {


    private String nomeCategoria;


    public CategoriaDTO(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;

    }

    public CategoriaDTO(){

    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
