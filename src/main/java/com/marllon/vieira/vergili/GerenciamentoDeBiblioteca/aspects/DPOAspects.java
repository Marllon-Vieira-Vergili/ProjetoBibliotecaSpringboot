package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.aspects;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.dto.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Aspect
@Component
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


            if(tempargs instanceof AutorDTO) {
                //agora vou falar pro joinpoint(ponto de junção pegar os argumentos em forma de array)
                AutorDTO autorDTO = (AutorDTO) tempargs;
                System.out.println("Autor nameDTO: " + autorDTO.getNome());
                System.out.println("Autor EmailDTO: " + autorDTO.getEmail());
                System.out.println("Autor TelefoneDTO: " + autorDTO.getTelefone());
                System.out.println("Autor CidadeDTO: " + autorDTO.getCidade());
            }
            else if (tempargs instanceof EmprestimoDTO) {
            EmprestimoDTO emprestimo = (EmprestimoDTO) tempargs;
            System.out.println("EmprestimoDTO dataEmprestimo: " + emprestimo.getDataEmprestimo());
            System.out.println("EmprestimoDTO dataDevolucao: " + emprestimo.getDataDevolucao());
            System.out.println("EmprestimoDTO estaEmprestado: " + emprestimo.isEstaEmprestado());

        }
            else if (tempargs instanceof LeitorDTO) {
            LeitorDTO leitor = (LeitorDTO) tempargs;
            System.out.println("Nome do leitorDTO: " + leitor.getNome());
            System.out.println("Sobrenome do leitorDTO: " + leitor.getSobrenome());
            System.out.println("Email do leitorDTO: " + leitor.getEmail());
        }
            else if (tempargs instanceof LivroDTO) {

            LivroDTO livro = (LivroDTO) tempargs;
            System.out.println("Nome do livroDTO: " + livro.getNome());
            System.out.println("Ano de Lançamento do livroDTO: " + livro.getAnoLancamento());

        } else if (tempargs instanceof CategoriaDTO) {

                CategoriaDTO categoria = (CategoriaDTO) tempargs;
                System.out.println("Nome da categoria: " + categoria.getNomeCategoria());

                }
            }
        }
    }