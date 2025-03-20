package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "autor")
public class Autor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Size(min = 5, max = 50)
    @Email(message = "Tipo de email inválido!")
    @NotBlank(message = "O email não pode estar vazio!")
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "telefone")
    private String telefone;

    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "cidade")
    private String cidade;


    //um autor pode ter muitos livros
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "autor", cascade ={CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.REMOVE})
    @JsonManagedReference
    private List<Livro> livros = new ArrayList<>();


    public Autor() {
    }

    public Autor(String nome, String email, String telefone, String cidade) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cidade = cidade;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }



    //private List<Livro> livrosTemp = new ArrayList<>();
    public List<Livro> getLivros() {
        if (livros.isEmpty()){
            return livros;

        }
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }



    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Autor autor)) return false;
        return id == autor.id;
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }

    //adicionar a assosiação do autor ao livro
    public void addLivro(Livro livro) {
        if(livros == null){
            livros = new ArrayList<>();
        }
        livro.addAutor(this);
    }
}
