package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "livro")
public class Livro implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Size(min = 4, max =  4)
    @Column(name = "ano_lancamento")
    private int anoLancamento;

    //um livro pode ter um autor
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    @Cascade({org.hibernate.annotations.CascadeType.DETACH, org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.REFRESH})
    @JsonBackReference
    private Autor autor;

    public Livro() {
    }

    public Livro(String nome, int anoLancamento) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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



    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    //adicionar a assosiação do autor ao livro
    public void addAutor(Autor autor) {
        this.autor = autor;
        autor.addLivro(this);

    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", anoLancamento=" + anoLancamento +
                '}';
    }
}
