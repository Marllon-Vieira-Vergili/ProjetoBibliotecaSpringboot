/*
package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class TestingAspects {


    @Before("com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects.BibliotecaPointcutsAOP.MostrarLogsDosMetodosdeTestes()")
    public void MostrarLogsDosMetodosdeTestes(JoinPoint joinPoint){ //joinpoint, chamada de método
        System.out.println("==========>>> Executando algum método do pacote de testes");

        //display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Método executado:  " + methodSignature);

        //display method arguments
    }
}


 */