

package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.testing;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.LeitorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeitorTesting {




    @Bean
    public CommandLineRunner runner(LeitorService leitorService) {

        return runner -> {
            //encontrarTodosOsLeitores(leitorRepository);
            //encontrarLeitorPorId(leitorRepository);
            //adicionarLeitor(leitorRepository);
            //atualizarLeitor(leitorRepository);
            //deletarLeitor(leitorRepository);

        };
    }


    private void deletarLeitor(LeitorService leitorService) {


    }


    private void atualizarLeitor(LeitorService leitorService) {


    }

    private void encontrarTodosOsLeitores(LeitorService leitorService) {


    }

    private void encontrarLeitorPorId(LeitorService leitorService) {

    }

    private void adicionarLeitor(LeitorService leitorService) {




    }

}


