package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "livro")
@Getter
@Setter
@NoArgsConstructor
public class Livro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "ano_lancamento")
    private int anoLancamento;

    //muitos livros pode ter um autor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_autor_id"))
    @Cascade({org.hibernate.annotations.CascadeType.DETACH, org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.REFRESH})
    @JsonBackReference
    private Autor autorRelacionado;

//muitos livros podem ter muitos leitores
    @ManyToMany(fetch = FetchType.LAZY, cascade =
            {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "listaLivrosRelacionadosAoLeitor")
    private List<Leitor> listaLeitoresRelacionados = new ArrayList<>();

//muitos livros podem ter muitos empréstimos
    @ManyToMany( fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "livro_tem_emprestimo", joinColumns = @JoinColumn(name = "livro_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_livro_para_emprestimo_id")),
            inverseJoinColumns = @JoinColumn(name = "emprestimo_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_emprestimo_para_livro_id")))
    private List<Emprestimo> listaEmprestimosRelacionados = new ArrayList<>();


    //Muitos livros podem ter muitas categorias
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "livro_tem_categoria", joinColumns = @JoinColumn(name = "livro_id",  referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_livro_para_categoria_id")),
            inverseJoinColumns = @JoinColumn(name = "categoria_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_categoria_para_livro")))
    @JsonManagedReference
    private List<Categoria> listaLivrosComCategoria = new ArrayList<>();



    public Livro(String nome, int anoLancamento) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;

    }



    /*LÒGICAS DE ASSOCIAÇÂO COM OUTRAS ENTIDADES (NO CASO, AUTOR E LIVRO, LIVRO E CATEGORIAS, LIVRO E EMPRÈSTIMO,
     LIVRO E LEITOR)*/




    public void associarLivroParaUmAutor(Autor autor) {

        //se o autor não estiver associado a esse livro
        if(!autor.equals(this.autorRelacionado)){
            this.autorRelacionado = autor; //associa autor ao livro
        }
        if(!autor.getListaLivrosDosAutores().contains(this)){
            autor.getListaLivrosDosAutores().add(this);
        }
    }


    //adicionar a assosiação de um livro a uma categoria

    public void associarLivroACategoria(Categoria categoria) {
        if(!listaLivrosComCategoria.contains(categoria)){
            listaLivrosComCategoria.add(categoria); //adicionando categoria ao livro
            categoria.getListaLivrosRelacionados().add(this); // Atualiza no lado da categoria (bidirecionalidade)

        }
    }


    public void associarLivroALeitor(Leitor leitor) {
        if (!listaLeitoresRelacionados.contains(leitor)) {
            listaLeitoresRelacionados.add(leitor);
            leitor.getListaLivrosRelacionadosAoLeitor().add(this); // Associa ao leitor também

        }
    }

    public void associarLivroAEmprestimo(Emprestimo emprestimo){
        if(!listaEmprestimosRelacionados.contains(emprestimo)){
            listaEmprestimosRelacionados.add(emprestimo);
            emprestimo.getListaLivrosEmprestados().add(this); //associação bidirecional
        }
    }


    //Métodos equals, HashCode e toString


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Livro livro)) return false;
        return Objects.equals(id, livro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
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
