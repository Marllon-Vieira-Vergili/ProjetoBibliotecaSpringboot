package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.associationsImplementations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.EmprestimoRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.EmprestimoELeitoresRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LeitorComEmprestimoRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.EmprestimoResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LeitorResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.EmprestimoELeitoresResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.EmprestimoELivrosResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LeitorComEmprestimoResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.EmprestimoRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LeitorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.LeitorService;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces.Emprestimo_Leitores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmprestimoLeitoresImpl implements Emprestimo_Leitores {


    @Autowired
    private LeitorRepository leitorRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;


    @Override
    public EmprestimoELeitoresResponseDTO associarEmprestimoALeitor(EmprestimoELeitoresRequestDTO emprestimoELeitoresRequestDTO) {

        //Encontrar o empréstimo pelo ID
        Emprestimo emprestimoEncontrado = emprestimoRepository.findById(emprestimoELeitoresRequestDTO.
                emprestimoId()).orElseThrow(() -> new NoSuchElementException("Nenhuma Id do empréstimo encontrada!"));


        //Encontrar o leitor pelo ID
        Leitor leitorEncontrado = leitorRepository.findById(emprestimoELeitoresRequestDTO.leitorId()).orElseThrow(()
                -> new NoSuchElementException("Nenhuma Id do empréstimo encontrada!"));

        //associar o emprestimo ao leitor
        emprestimoEncontrado.associarEmprestimoALeitor(leitorEncontrado);
        emprestimoEncontrado.setEstaEmprestado(true);

        //salvar as associacoes
        emprestimoRepository.save(emprestimoEncontrado);

        //converter os dados para uma DTO, e imprimir para o usuário
        List<EmprestimoResponseDTO> emprestimoResponseDTOS = Collections.singletonList(new
                EmprestimoResponseDTO(emprestimoEncontrado.getId(), emprestimoEncontrado.getDataEmprestimo(),
                emprestimoEncontrado.getDataDevolucao(), emprestimoEncontrado.isEstaEmprestado()));



        //Retornar a vizualização da DTO para o usuário
        return new EmprestimoELeitoresResponseDTO(leitorEncontrado.getId(), leitorEncontrado.getNome(),
                leitorEncontrado.getSobrenome(), leitorEncontrado.getIdade(), leitorEncontrado.getEmail(),
                emprestimoResponseDTOS.stream().toList());
    }

    @Override
    public LeitorComEmprestimoResponseDTO associarLeitorAEmprestimo(LeitorComEmprestimoRequestDTO leitorComEmprestimoRequestDTO) {


        //Encontrar o leitor pelo ID
        Leitor leitorEncontrado = leitorRepository.findById(leitorComEmprestimoRequestDTO.LeitorId()).orElseThrow(()
                -> new NoSuchElementException("Nenhuma Id do empréstimo encontrada!"));

        //Encontrar o empréstimo pelo ID
        Emprestimo emprestimoEncontrado = emprestimoRepository.findById(leitorComEmprestimoRequestDTO.EmprestimoId()).
                orElseThrow(() -> new NoSuchElementException("Nenhuma Id do empréstimo encontrada!"));

        //associar o emprestimo ao leitor
        leitorEncontrado.associarLeitorAEmprestimo(emprestimoEncontrado);
        emprestimoEncontrado.setEstaEmprestado(true);

        //salvar as associacoes
        leitorRepository.save(leitorEncontrado);


        //converter os dados para uma DTO, e imprimir para o usuário
        List<EmprestimoResponseDTO> emprestimoResponseDTOS = Collections.singletonList(new
                EmprestimoResponseDTO(emprestimoEncontrado.getId(), emprestimoEncontrado.getDataEmprestimo(),
                emprestimoEncontrado.getDataDevolucao(), emprestimoEncontrado.isEstaEmprestado()));


        //Retornar para o usuário verificar como foi adicionado
        return new LeitorComEmprestimoResponseDTO(emprestimoResponseDTOS, leitorEncontrado.getId(),
                leitorEncontrado.getNome(), leitorEncontrado.getSobrenome(), leitorEncontrado.getEmail(),
                leitorEncontrado.getIdade());
    }

    @Override
    public EmprestimoELeitoresResponseDTO encontrarEmprestimoDeLeitor(Integer id) {

        //Encontrar o leitor
        Leitor leitorEncontrado = leitorRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Nenhuma Id do empréstimo encontrada!"));

        //Encontrar o emprestimo
        Emprestimo emprestimoEncontrado = emprestimoRepository.findById(id)
                .orElseThrow(()
                -> new NoSuchElementException("Nenhuma Id do empréstimo encontrada!"));;

        //Converter os dados para sair no formato DTO
        List<EmprestimoResponseDTO> emprestimoResponseDTOS = Collections.singletonList(new EmprestimoResponseDTO
                (emprestimoEncontrado.getId(), emprestimoEncontrado.getDataEmprestimo(),
                        emprestimoEncontrado.getDataDevolucao(), emprestimoEncontrado.isEstaEmprestado()));


        //Retornar o DTO para o usuário vizualizar
        return new EmprestimoELeitoresResponseDTO(leitorEncontrado.getId(), leitorEncontrado.getNome(),
                leitorEncontrado.getSobrenome(), leitorEncontrado.getIdade(), leitorEncontrado.getEmail(),
                emprestimoResponseDTOS);
    }
}
