
/*
package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.*;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Aspect
@Component
@Order(value = 3)
public class DPOAspects {

    //Antes de executar qualquer método do pacote DTO... faça isso
    @Before("com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects.BibliotecaPointcutsAOP.MostrarLogsDosMetodosDTO())")
    public void MostrarLogDosMetodosDTO(JoinPoint joinPoint){

        //Instanciar um Logger que vai gerar log, que vou pegar o log, de uma classe, pegando o nome da classe
        Logger meuLogger = Logger.getLogger(getClass().toString());
        System.out.println("==========>>>>> Executando um método do pacote DTO");

        //Join point vai pegar a assinatura e me devolver para uma string
        joinPoint.getSignature().toShortString();


        //agora vou falar pro joinpoint(ponto de junção pegar os argumentos em forma de array)
        for(Object tempargs : joinPoint.getArgs()){ //para cada argumento de objeto que o joinpoint.pegar argumentos achar..
            meuLogger.info("Argumentos: " + tempargs); //Meu logger vai me mandar os argumentos


            if(tempargs instanceof AutorResponseDTO) {
                //agora vou falar pro joinpoint(ponto de junção pegar os argumentos em forma de array)
                AutorResponseDTO autorDTO = (AutorResponseDTO) tempargs;
                System.out.println("Autor nameDTO: " + autorDTO.nome());
                System.out.println("Autor EmailDTO: " + autorDTO.email());
                System.out.println("Autor TelefoneDTO: " + autorDTO.telefone());
                System.out.println("Autor CidadeDTO: " + autorDTO.cidade());
            }
            else if (tempargs instanceof EmprestimoResponseDTO) {
            EmprestimoResponseDTO emprestimo = (EmprestimoResponseDTO) tempargs;
            System.out.println("EmprestimoDTORecord dataEmprestimo: " + emprestimo.dataEmprestimo());
            System.out.println("EmprestimoDTORecord dataDevolucao: " + emprestimo.dataDevolucao());
            //System.out.println("EmprestimoDTORecord estaEmprestado: " + emprestimo.isEstaEmprestado());

        }
            else if (tempargs instanceof LeitorResponseDTO) {
            LeitorResponseDTO leitor = (LeitorResponseDTO) tempargs;
            System.out.println("Nome do leitorDTO: " + leitor.nome());
            System.out.println("Sobrenome do leitorDTO: " + leitor.sobrenome());
            System.out.println("Email do leitorDTO: " + leitor.email());
        }
            else if (tempargs instanceof LivroResponseDTO) {

            LivroResponseDTO livro = (LivroResponseDTO) tempargs;
            System.out.println("Nome do livroDTO: " + livro.nome());
            System.out.println("Ano de Lançamento do livroDTO: " + livro.anoLancamento());

        } else if (tempargs instanceof CategoriaResponseDTO) {

                CategoriaResponseDTO categoria = (CategoriaResponseDTO) tempargs;
                System.out.println("Nome da categoria: " + categoria.nomeCategoria());

                }
            }
        }
    }



 */