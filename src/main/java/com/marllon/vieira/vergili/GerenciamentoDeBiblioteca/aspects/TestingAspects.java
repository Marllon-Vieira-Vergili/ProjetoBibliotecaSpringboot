
package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Order(3)
public class TestingAspects {


    @Before("com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects.BibliotecaPointcutsAOP.MostrarLogsDosMetodosdeTestes())")
    public void MostrarLogsDosMetodosdeTestes(JoinPoint joinPoint){ //joinpoint, chamada de método
        System.out.println("==========>>> Executando algum método do pacote de testes");

        //Instanciando um Logger que vai gerar log, que vou pegar o log, de uma classe, pegando o nome da classe
        final Logger meuLog = Logger.getLogger(joinPoint.getClass().getName());


        //display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Método executado:  " + methodSignature);

        //display method arguments
        for (Object arg : joinPoint.getArgs()){
            meuLog.info("===========>>>> Argumentos: " + arg);

        }
    }
}

