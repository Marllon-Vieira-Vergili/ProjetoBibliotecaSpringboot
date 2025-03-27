package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Entity
@Table(name = "autor")
@Getter
@Setter
@NoArgsConstructor //Já define um construtor vazio automaticamente
public class Autor {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "autorRelacionado", cascade ={CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.REMOVE})
    @JsonManagedReference
    private List<Livro> listaLivrosDosAutores = new ArrayList<>();

    public Autor(String nome, String email, String telefone, String cidade) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cidade = cidade;
    }


    //LÒGICAS DE ASSOCIAÇÂO COM OUTRAS ENTIDADES (NO CASO, AUTOR E LIVRO)

    //adicionar a assosiação do autor a sua lista de livros


    public void associarAutorParaUmLivro(Livro livro) {

        if(!listaLivrosDosAutores.contains(livro)){
            listaLivrosDosAutores.add(livro);
            livro.setAutorRelacionado(this); //Adicionar no atributo da lista do arraylist do livro esse autor associado
        }
    }

    //Gette e setter para Setar ou pegar um livro somente da lista
    public Livro obterUmLivroDoAutor(Livro livro) {
        for (Livro oneLivro : listaLivrosDosAutores) {
            if (oneLivro.equals(livro)) {
                return oneLivro;
            }
        }
        return null;
    }


    public void associarUmLivroParaOutroAutor(Livro livro, Autor novoAutor){

        Livro encontrarLivro = obterUmLivroDoAutor(livro);

        if(encontrarLivro != null){
            encontrarLivro.setAutorRelacionado(novoAutor);
            encontrarLivro.getAutorRelacionado().getListaLivrosDosAutores().remove(encontrarLivro);

            //adicionar o livro a lista do novo autor
            if(!novoAutor.getListaLivrosDosAutores().contains(livro)){
                novoAutor.getListaLivrosDosAutores().add(encontrarLivro);
            }
        }else{
            throw new NoSuchElementException("Livro não encontrado na lista!");
        }
    }



    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Autor autor)) return false;
        return Objects.equals(id, autor.id);
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
}
