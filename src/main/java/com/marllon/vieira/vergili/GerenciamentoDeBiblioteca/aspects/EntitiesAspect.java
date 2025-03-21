
package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Order(1)
public class EntitiesAspect {


    @AfterReturning("execution(com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects.BibliotecaPointcutsAOP.MostrarLogsDosMetodosdeEntitidades()(..))")
    public void depoisExecutarTalMetodo() {

        //aqui podemos criar método para mostrar os dados que nossos métodos passou depois de executado
        //e também até modifica-los! como por exemplo.. formatando a saída de todos os dados para Uppercase

        /*
    Ele é útil quando você quer:
inspecionar ou modificar o valor retornado
registrar logs com base no retorno
fazer auditoria
monitoramento de performance
ou mesmo aplicar alguma lógica com base no resultado do método.


         */
    }

@Before("com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects.BibliotecaPointcutsAOP.MostrarLogsDosMetodosdeEntitidades())")
public void MostrarLogsMetodosEntidades(JoinPoint joinPoint) {
        System.out.println(" =====>>> Executando algum método do pacote de Entidades");

    Logger logger = Logger.getLogger(getClass().getName());

        //Mostrar a assinatura do método
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Metodo: " + methodSignature);
        //display method arguments

        //Pegar os argumentos do Join point.. como são vários, vamos instanciar um array
        Object[] args = joinPoint.getArgs();

        //Loop sobre estes argumentos
        for (Object tempArgs : args) {
            System.out.println(tempArgs);

            if (tempArgs instanceof Autor) {

                Autor autor = (Autor) tempArgs;
                System.out.println("Autor name: " + autor.getNome());
                System.out.println("Autor Email: " + autor.getEmail());
                System.out.println("Autor Telefone: " + autor.getTelefone());
                System.out.println("Autor Cidade: " + autor.getCidade());

            } else if (tempArgs instanceof Emprestimo) {
                Emprestimo emprestimo = (Emprestimo) tempArgs;
                System.out.println("Emprestimo dataEmprestimo: " + emprestimo.getDataEmprestimo());
                System.out.println("Emprestimo dataDevolucao: " + emprestimo.getDataDevolucao());
                System.out.println("Emprestimo estaEmprestado: " + emprestimo.isEstaEmprestado());

            } else if (tempArgs instanceof Leitor) {
                Leitor leitor = (Leitor) tempArgs;
                System.out.println("Nome do leitor: " + leitor.getNome());
                System.out.println("Sobrenome do leitor: " + leitor.getSobrenome());
                System.out.println("Email do leitor: " + leitor.getEmail());
            } else if (tempArgs instanceof Livro) {

                Livro livro = (Livro) tempArgs;
                System.out.println("Nome do livro: " + livro.getNome());
                System.out.println("Ano de Lançamento do livro: " + livro.getAnoLancamento());

            } else if (tempArgs instanceof Categoria) {

                Categoria categoria = (Categoria) tempArgs;
                System.out.println("Nome da categoria: " + categoria.getNomeCategoria());

            }
        }
    }
}
