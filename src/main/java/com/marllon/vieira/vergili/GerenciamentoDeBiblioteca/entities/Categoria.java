package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;


import java.util.*;

@Entity
@Table(name="categoria")
public class Categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private int id;

    @Column(name = "nome_categoria")
    @NotNull
    private String nomeCategoria;

    //muitas categorias podem ter muitos livros, ordenados em sequência pela sua categoria
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categorias")
    @JsonBackReference
    private List<Livro> livrosCategoria;


    public Categoria(){
    }

    public Categoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public List<Livro> getLivrosCategoria() {
        return livrosCategoria;
    }

    public void setLivrosCategoria(List<Livro> livrosCategoria) {
        this.livrosCategoria = livrosCategoria;
    }

    //associar uma categoria a um livro
    public void addCategoriaToLivro(Livro livro) {
        if(this.livrosCategoria == null){
            this.livrosCategoria = new ArrayList<>();
        }
        this.livrosCategoria.add(livro);
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome_categoria='" + nomeCategoria + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Categoria categoria)) return false;
        return id == categoria.id && Objects.equals(nomeCategoria, categoria.nomeCategoria);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
