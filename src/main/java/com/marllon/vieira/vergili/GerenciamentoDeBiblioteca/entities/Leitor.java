package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "leitor")
public class Leitor {



    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nome")
    @NotNull
    @Size(min = 5, max = 50)
    private String nome;

    @Column(name="sobrenome")
    @NotNull
    @Size(min = 5, max = 50)
    private String sobrenome;


    @Column(name="email")
    @NotNull
    @Email(message = "Tipo de email inválido!")
    @NotBlank(message = "O email não pode estar vazio!")
    @Size(min = 5, max = 50)
    private String email;

    @Column(name="idade")
    @NotNull
    private int idade;

    //muitos leitores podem ter muitos livros
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE })
    @JoinTable(name = "leitor_com_livro", joinColumns = @JoinColumn(name = "leitor_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(name = "fk_leitor_id"), inverseJoinColumns =
    @JoinColumn(name = "livro_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_livro_id")))
    private List<Livro> livro;

    //muitos leitores podem ter muitos empréstimos
@ManyToMany(fetch = FetchType.EAGER, mappedBy = "emprestimoParaLeitores")
@Cascade({org.hibernate.annotations.CascadeType.DETACH, org.hibernate.annotations.CascadeType.MERGE,
        org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.REFRESH})
    private List<Emprestimo> leitorEmprestimos;



    public Leitor(){

    }

    public Leitor(String nome, String sobrenome, String email, int idade) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.idade = idade;
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

    public List<Livro> getLivro() {
        return livro;
    }

    public void setLivro(List<Livro> livro) {
        this.livro = livro;
    }


    public List<Emprestimo> getLeitorEmprestimos() {
        return leitorEmprestimos;
    }

    public void setLeitorEmprestimos(List<Emprestimo> leitorEmprestimos) {
        this.leitorEmprestimos = leitorEmprestimos;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Leitor leitor)) return false;
        return id == leitor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Leitor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + idade +
                '}';
    }
}
