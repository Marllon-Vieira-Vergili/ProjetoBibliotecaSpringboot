package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "leitor")
@Getter
@Setter
@NoArgsConstructor
public class Leitor {



    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    private Integer idade;

    //muitos leitores podem ter muitos livros
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "leitor_com_livro", joinColumns = @JoinColumn(name = "leitor_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(name = "fk_leitor_id"), inverseJoinColumns =
    @JoinColumn(name = "livro_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_livro_id")))
    private List<Livro> listaLivrosRelacionadosAoLeitor = new ArrayList<>();

    //muitos leitores podem ter muitos empréstimos
@ManyToMany(fetch = FetchType.LAZY, mappedBy = "listaLeitoresComEmprestimos")
@Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Emprestimo> listaEmprestimosRelacionadosAoLeitor = new ArrayList<>();



      /*LÒGICAS DE ASSOCIAÇÂO COM OUTRAS ENTIDADES (NO CASO, LEITOR E LIVRO, LEITOR E EMPRESTIMO)*/



    public void associarLeitorALivro(Livro livro){
        if(!listaLivrosRelacionadosAoLeitor.contains(livro)){
            listaLivrosRelacionadosAoLeitor.add(livro);
            livro.getListaLeitoresRelacionados().add(this);
        }
    }


    public void associarLeitorAEmprestimo(Emprestimo emprestimo){
        if(!listaEmprestimosRelacionadosAoLeitor.contains(emprestimo)){
            listaEmprestimosRelacionadosAoLeitor.add(emprestimo);
            emprestimo.getListaLeitoresComEmprestimos().add(this);
        }
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
