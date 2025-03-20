package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "data_emprestimo")
    @NotNull(message = "Digite a data do empréstimo")
    @NotBlank(message = "Não pode ficar em branco!")
    private Instant  dataEmprestimo;

    @Column(name = "data_devolucao")
    @NotNull(message = "Digite a data da devolução do livro")
    @NotBlank(message = "digite quando o livro foi devolvido!")
    private Instant dataDevolucao;

    @Column(name = "esta_emprestado")
    @NotNull(message = "SIM OU NÂO")
    private boolean estaEmprestado;


    //Muitos empréstimos podem ter muitos livros
    @ManyToMany(mappedBy = "livroEmprestimos",fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<Livro> livros;


    //muitos empréstimos podem ter muitos leitores
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "emprestimo_para_leitor", joinColumns = @JoinColumn(name = "emprestimo_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_emprestimo_para_leitor_id")),
            inverseJoinColumns = @JoinColumn(name = "leitor_id",referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_leitor_para_emprestimo_id")))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Leitor> emprestimoParaLeitores;



    //Construtor vazio
    public Emprestimo(){

    }

    public Emprestimo(Instant dataEmprestimo, Instant dataDevolucao, boolean estaEmprestado) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.estaEmprestado = estaEmprestado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instant getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Instant dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Instant getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Instant dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isEstaEmprestado() {
        return estaEmprestado;
    }

    public void setEstaEmprestado(boolean estaEmprestado) {
        this.estaEmprestado = estaEmprestado;
    }

    public List<Leitor> getEmprestimoParaLeitores() {
        return emprestimoParaLeitores;
    }

    public void setEmprestimoParaLeitores(List<Leitor> emprestimoParaLeitores) {
        this.emprestimoParaLeitores = emprestimoParaLeitores;
    }

    public List<Livro> getLivros() {
        if (getLivros().isEmpty()){
            throw new NoSuchElementException("Não há nenhum elemento na lista de livros");
        }
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
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
