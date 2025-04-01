
package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(4)
public class ServicesAspects {

    @Before("com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects.BibliotecaPointcutsAOP.MostrarLogsDosMetodosdeServicos())")
    public void MostrarLogsDosMetodosdeServicos(JoinPoint joinPoint){
        System.out.println("==========>>> Executando algum método do pacote de Services");

        //pegar a assinatura do método
        String method = joinPoint.getSignature().toShortString();
        System.out.println("Método sendo executado: " + method);

        /*
        //exibir os argumentos dos métodos
        Object[] args = joinPoint.getArgs();
        for(Object tempArgs: args){
            System.out.println(tempArgs);

            if (tempArgs instanceof Livro){
                Livro theLivro = (Livro) tempArgs;)
        }

         */
    }
}


