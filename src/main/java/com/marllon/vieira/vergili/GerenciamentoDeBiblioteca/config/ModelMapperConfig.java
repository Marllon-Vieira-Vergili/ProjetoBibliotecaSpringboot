package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {


    //Model mapper vai pegar os dados das entidades, e converter para um json mais limpo, com minhas classes DAO.
    //instanciando ele como novo modelmapper para passar de parâmetro os dados das DAOs
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
