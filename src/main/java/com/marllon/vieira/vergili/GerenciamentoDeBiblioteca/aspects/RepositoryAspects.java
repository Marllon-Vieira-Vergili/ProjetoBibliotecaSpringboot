
package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.CategoriaRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.EmprestimoRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LeitorRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;




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

            if (tempArgs instanceof AutorRepository) {
                AutorRepository autorRepository = (AutorRepository) joinPoint;
                System.out.println("Acionado Repositorio do autor: " + autorRepository.getClass());

            } else if (tempArgs instanceof EmprestimoRepository) {
                EmprestimoRepository emprestimoRepository = (EmprestimoRepository) joinPoint;
                System.out.println("Acionado emprestimo: " + emprestimoRepository.getClass());

            } else if (tempArgs instanceof LeitorRepository) {
                LeitorRepository leitorRepositoryImplementation = (LeitorRepository) joinPoint;
                System.out.println("Acionado leitor: " + leitorRepositoryImplementation.getClass());

            } else if (tempArgs instanceof CategoriaRepository) {
                CategoriaRepository categoriaRepositoryImplement = (CategoriaRepository) joinPoint;
                System.out.println("Acionado categoria: " + categoriaRepositoryImplement.getClass());
            }
        }
    }
}



