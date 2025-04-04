package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "emprestimo")
@Getter
@Setter
@NoArgsConstructor
public class Emprestimo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Column(name = "data_emprestimo")
    //@NotNull(message = "Digite a data do empréstimo")
    private LocalDate  dataEmprestimo;

    @Column(name = "data_devolucao")
    //@NotNull(message = "Digite a data da devolução do livro")
    private LocalDate dataDevolucao;

    @Column(name = "esta_emprestado")
    @NotNull(message = "TRUE ou FALSE")
    private boolean estaEmprestado = false;


    //Muitos empréstimos podem ter muitos livros
    @ManyToMany(mappedBy = "listaEmprestimosRelacionados",fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<Livro> listaLivrosEmprestados = new ArrayList<>();


    //muitos empréstimos podem ter muitos leitores
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "emprestimo_para_leitor", joinColumns = @JoinColumn(name = "emprestimo_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_emprestimo_para_leitor_id")),
            inverseJoinColumns = @JoinColumn(name = "leitor_id",referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_leitor_para_emprestimo_id")))
    private List<Leitor> listaLeitoresComEmprestimos = new ArrayList<>();




    public Emprestimo(LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;

    }

      /*LÒGICAS DE ASSOCIAÇÂO COM OUTRAS ENTIDADES (NO CASO, EMPRESTIMO A LIVRO, E EMPRESTIMO A LEITOR)*/



    //Métodos de Associações

    public void associarEmprestimoALivro(Livro livro){
        if(!listaLivrosEmprestados.contains(livro)){
            listaLivrosEmprestados.add(livro);
            livro.getListaEmprestimosRelacionados().add(this); //Adicionar no atributo da lista do arraylist do livro esse empréstimo associado
        }
    }

    public void associarEmprestimoALeitor(Leitor leitor){
            if(!listaLeitoresComEmprestimos.contains(leitor)){
                listaLeitoresComEmprestimos.add(leitor);
                leitor.getListaEmprestimosRelacionadosAoLeitor().add(this); //Adicionar no atributo da lista do arraylist do leitor esse empréstimo associado
            }
    }


    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", estaEmprestado=" + estaEmprestado +
                '}';
    }
}
