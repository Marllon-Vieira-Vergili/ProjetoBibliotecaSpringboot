/*
package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Order(5)
public class ControllerAspects {

    //@Before("com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects.BibliotecaPointcutsAOP.MostrarLogsDosMetodosdeRestControllers()")


    //Instanciando um Logger que vai gerar log, que vou pegar o log, de uma classe, pegando o nome da classe
    private Logger meuLog = Logger.getLogger(getClass().getName());


    @Before("LerOaspectoDeQualquerPacote()")
    public void MostrarLogsDosMetodosdeRestControllersAntesExecucao(JoinPoint joinPoint){


        System.out.println("==========>>> Executando algum método do pacote restController");

        //mostrar o método que estamos chamando...
        String method = joinPoint.getSignature().toShortString();
        System.out.println("=========> @Before(Antes): chamando o método:  " + method);

        //pegar os argumentos desse método
        Object[] argumentos = joinPoint.getArgs();

        //Vamos percorrer todo o array destes argumentos e mostrá-los
        for(Object tempArgumento: argumentos){
            meuLog.info("=======> argumentos: " + tempArgumento);
        }
    }


    //Depois adicionando @AfterReturn aviso
    @AfterReturning(pointcut = "MostrarLogsDosMetodosdeRestControllersAntesExecucao", returning = "resultado")
    public void DepoisdeExecutarMetodoRestController(JoinPoint theJoinPoint, Object resultado){

        //Imprimir que o método será executado após a execução do método
        System.out.println("==========>>>>Executando o método @AfterReturning depois que o método chamado foi executado: ");

        //Mostrar um log no console, qual método foi chamado
        String method = theJoinPoint.getSignature().toShortString();
        meuLog.info(" ==========>>> Executando o método @AfterReturning Chamando o método: " + method);


        //mostrar o resultado
        meuLog.info("===========>>>>>>> Resultado: " + resultado);
    }

}


 */