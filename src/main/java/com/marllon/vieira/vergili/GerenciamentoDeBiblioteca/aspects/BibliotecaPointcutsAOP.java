

package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BibliotecaPointcutsAOP {

//Pointcut para mostrar os logs dos métodos de todos os pacotes


    //Pointcut para mostrar os logs dos métodos de entidades
    @Pointcut("execution(* com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.*.*(..))")
    public void MostrarLogsDosMetodosdeEntitidades() {
    }

    //Pointcut para mostrar os logs dos métodos de servicos
    @Pointcut("execution(* com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.*.*(..))")
    public void MostrarLogsDosMetodosdeServicos() {
    }

    //Pointcut para mostrar os logs dos métodos de servicos
    @Pointcut("execution(* com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.security.*.*(..))")
    public void MostrarLogsDosMetodosdeSeguranca() {
    }

    //Pointcut para mostrar os logs dos métodos de Repositórios
    @Pointcut("execution(* com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.*.*(..))")
    public void MostrarLogsDosMetodosdeRepositorios() {
    }

    //Pointcut para mostrar os logs dos métodos de Rest Controllers
    @Pointcut("execution(* com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.controllers.*.*(..))")
    public void MostrarLogsDosMetodosdeRestControllers() {
    }

    //Pointcut para mostrar os logs dos métodos de DTO
    @Pointcut("execution(* com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.*.*(..))")
    public void MostrarLogsDosMetodosDTO(){
    }
}



/*
    //PointCut para Ler de todos estes métodos juntos, fazendo uma condição OU
    @Pointcut("MostrarLogsDosMetodosdeEntitidades() || " +
            "MostrarLogsDosMetodosdeServicos() || MostrarLogsDosMetodosdeRepositorios()|| " +
            "MostrarLogsDosMetodosdeRestControllers() || MostrarLogsDosMetodosdeTestes() || MostrarLogsDosMetodosdeSeguranca()")
    public void LerOaspectoDeQualquerPacote(){}
}

 */




