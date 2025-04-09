package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;


import java.util.*;

@Entity
@Table(name="categoria")
@Getter
@Setter
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome_categoria")
    @NotNull(message = "Nome categoria não pode ser nulo!")
    @NotBlank(message = "Não pode ficar em branco!")
    private String nomeCategoria;

    //muitas categorias podem ter muitos livros, ordenados em sequência pela sua categoria
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "listaLivrosComCategoria", cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonBackReference
    private List<Livro> listaLivrosRelacionados = new ArrayList<>();


    //LÒGICAS DE ASSOCIAÇÂO COM OUTRAS ENTIDADES (NO CASO, CATEGORIA COM LIVROS)

    //associar uma categoria a um livro

    public Categoria obterCategoriaDeUmLivro(Integer livroId){
        for(Livro oneLivro: listaLivrosRelacionados){
            if(oneLivro.getId().equals(livroId)){
                return (Categoria) oneLivro.getListaLivrosComCategoria();
            }
        }
        return null;
    }

    public void associarCategoriaAoLivro(Livro livro){
        if(!listaLivrosRelacionados.contains(livro)){
            listaLivrosRelacionados.add(livro); // Adiciona o livro à lista de livros relacionados
            livro.getListaLivrosComCategoria().add(this); //bidirecionalidade
        }
    }



    //ToString, equals e HashCode da entidade Categoria


    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nomeCategoria='" + nomeCategoria + '\'' +
                ", listaLivrosRelacionados=" + listaLivrosRelacionados +
                '}';
    }


    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Categoria categoria)) return false;
        return Objects.equals(id, categoria.id);
    }

    //Comparar se os dois objetos serão iguais, com o mesmo código hash
    @Override
    public int hashCode() {
        return Objects.hash(id,nomeCategoria);
    }


}
