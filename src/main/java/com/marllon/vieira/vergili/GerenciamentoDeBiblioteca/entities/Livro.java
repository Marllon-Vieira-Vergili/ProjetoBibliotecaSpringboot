package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Column(name = "ano_lancamento")
    private int anoLancamento;

    //muitos livros pode ter um autor
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_autor_id"))
    @Cascade({org.hibernate.annotations.CascadeType.DETACH, org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.REFRESH})
    @JsonBackReference
    private Autor autor;

//muitos livros podem ter muitos leitores
    @ManyToMany(fetch = FetchType.EAGER, cascade =
            {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "livro")
    private List<Leitor> leitor;

//muitos livros podem ter muitos empréstimos
    @ManyToMany( fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "livro_tem_emprestimo", joinColumns = @JoinColumn(name = "livro_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_livro_para_emprestimo_id")),
            inverseJoinColumns = @JoinColumn(name = "emprestimo_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_emprestimo_para_livro_id")))
    private List<Emprestimo> livroEmprestimos;


    //Muitos livros podem ter muitas categorias
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "livro_tem_categoria", joinColumns = @JoinColumn(name = "livro_id",  referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_livro_para_categoria_id")),
            inverseJoinColumns = @JoinColumn(name = "categoria_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_categoria_para_livro")))
    private List<Categoria> categorias;


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

    public List<Emprestimo> getLivroEmprestimos() {
        return livroEmprestimos;
    }

    public void setLivroEmprestimos(List<Emprestimo> livroEmprestimos) {
        this.livroEmprestimos = livroEmprestimos;
    }

    public List<Leitor> getLeitor() {
        return leitor;
    }

    public void setLeitor(List<Leitor> leitor) {
        this.leitor = leitor;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
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
        if(autor.getLivros() == null){ //Se o autor ainda não estiver com nenhum livro
            //Vamos instanciar uma nova lista de arrays de livro pra ele
            autor.setLivros(new ArrayList<>());
        }
        autor.addLivro(this); //associando o autor ao livro

    }


    //adicionar a assosiação de um livro a uma categoria
    public void addCategoria(Categoria categoria) {
        if(this.categorias == null){
            this.categorias = new ArrayList<>();
        }
        this.categorias.add(categoria);
    }

    //associar o livro ao leitor
    public void addLeitor(Leitor leitor){
        this.leitor.add(leitor);
        if(leitor.getLivro() == null){
            leitor.setLivro(new ArrayList<>());
        }
        leitor.getLivro().add(this); //associação bidirencional
    }


    //adicionar um empréstimo a um livro
    public void addEmprestimo(Emprestimo emprestimo){
        this.livroEmprestimos.add(emprestimo);
        emprestimo.getLivros().add(this); //associação bidirecional
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Livro livro)) return false;
        return id == livro.id;
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
