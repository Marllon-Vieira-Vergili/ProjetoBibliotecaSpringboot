/*
package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Categoria;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.AutorRepositoryImplement;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.CategoriaRepositoryImplement;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.EmprestimoRepositoryImplementation;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repositories.repoImpl.LeitorRepositoryImplementation;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.EmprestimoRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;


@Aspect
@Component
@Order(2)
public class RepositoryAspects {


    @Before("com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects.BibliotecaPointcutsAOP.MostrarLogsDosMetodosdeRepositorios())")
    public void MostrarLogsDosMetodosdeRepositorios(JoinPoint joinPoint) {
        System.out.println(" =====>>> Executando algum método do pacote de Repositório");

        //display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Metodo: " + methodSignature);
        //display method arguments

        //Pegar os argumentos do Join point.. como são vários, vamos instanciar um array
        Object[] args = joinPoint.getArgs();

        //Loop sobre estes argumentos
        for (Object tempArgs : args) {
            System.out.println(tempArgs);

            if (tempArgs instanceof AutorRepositoryImplement) {
                AutorRepositoryImplement autorRepositoryImplement = (AutorRepositoryImplement) joinPoint;
                System.out.println("Acionado autor: " + autorRepositoryImplement.getClass());

            } else if (tempArgs instanceof EmprestimoRepository) {
                EmprestimoRepositoryImplementation emprestimoRepository = (EmprestimoRepositoryImplementation) joinPoint;
                System.out.println("Acionado emprestimo: " + emprestimoRepository.getClass());
            } else if (tempArgs instanceof LeitorRepositoryImplementation) {
                LeitorRepositoryImplementation leitorRepositoryImplementation = (LeitorRepositoryImplementation) joinPoint;
                System.out.println("Acionado leitor: " + leitorRepositoryImplementation.getClass());
            } else if (tempArgs instanceof CategoriaRepositoryImplement) {
                CategoriaRepositoryImplement categoriaRepositoryImplement = (CategoriaRepositoryImplement) joinPoint;
                System.out.println("Acionado categoria: " + categoriaRepositoryImplement.getClass());
            }
        }



        @AfterReturning(value = "execution(* com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor.*(..))"
                ,returning = "autorDataReturning")
        public void RetornarTodosOsAutores (JoinPoint theJoinPoint, List < Autor > autorDataReturning){

            String method = theJoinPoint.getSignature().toShortString();
            System.out.println(" =====>>> Executando o metodo depois do retorno dele: " + method);

            System.out.println("========>>> resultado é: " + autorDataReturning);


        }
    }
}



 */

