package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name="categoria")
public class Categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    @NotNull
    @NotEmpty
    private int id;

    @Column(name = "nome_categoria")
    @NotNull
    private String nomeCategoria;

    //muitas categorias podem ter muitos livros, ordenados em sequência pela sua categoria
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categorias")
    private Set<Livro> livrosCategoria = new TreeSet<>();


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

    public Set<Livro> getLivrosCategoria() {
        return livrosCategoria;
    }

    public void setLivrosCategoria(Set<Livro> livrosCategoria) {
        this.livrosCategoria = livrosCategoria;
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
