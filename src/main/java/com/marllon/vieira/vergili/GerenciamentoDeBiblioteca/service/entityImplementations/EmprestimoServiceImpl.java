package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityImplementations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.EmprestimoRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.EmprestimoRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;



    @Override
    public Emprestimo criarEmprestimo(EmprestimoRequest emprestimoRequest) {

        //Criar novo Emprestimo
        Emprestimo novoEmprestimo = new Emprestimo();
        novoEmprestimo.setDataEmprestimo((emprestimoRequest.dataEmprestimo())); //Retornando localDate
        novoEmprestimo.setDataDevolucao((emprestimoRequest.dataDevolucao())); //Retornando localDate
        novoEmprestimo.setEstaEmprestado(true);//Sempre que criar um novo emprestimo, ele vai estar emprestado, valor true

        //Converter os valores do novo emprestimo para o padrão pt-br
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateTimeFormatter.format(novoEmprestimo.getDataEmprestimo());
        dateTimeFormatter.format(novoEmprestimo.getDataDevolucao());


        //1. Validar formato da data de Emprestimo, se está a partir do dia atual
        if(novoEmprestimo.getDataEmprestimo() == null ||
                novoEmprestimo.getDataEmprestimo().isBefore(LocalDate.now()) ||
                        !novoEmprestimo.getDataEmprestimo().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Por favor, Digite uma data Válida entre o dia atual de hoje!(dia/mes/ano)");
        }

        //2. Validar formato da data de Devolucao, se está do dia atual em diante
        if(novoEmprestimo.getDataDevolucao() == null ||
                novoEmprestimo.getDataDevolucao().isBefore(novoEmprestimo.getDataEmprestimo())){
                    throw new IllegalArgumentException("A Data de devolução deve ser maior ou igual a data de emprestimo!");
        }


        //Verificação para verificar se esse emprestimo já não existe
        for(Emprestimo emprestimosPercorridas: emprestimoRepository.findAll()){
            if (emprestimosPercorridas.getDataEmprestimo().equals(novoEmprestimo.getDataEmprestimo())){
               if(emprestimosPercorridas.getDataDevolucao().equals(novoEmprestimo.getDataDevolucao())){
                   throw new IllegalStateException("Esse emprestimo já existe no banco de dados!");
               }
            }//Se a data de emprestimo e devolução não forem iguais, criar o primeiro emprestimo de toda lista

            else if (emprestimoRepository.findAll().isEmpty()) {
                //Se for o primeiro emprestimo criado, vamos adicioná-la em um nova lista
                Emprestimo primeiroEmprestimo = new Emprestimo();
                primeiroEmprestimo.setDataEmprestimo((emprestimoRequest.dataEmprestimo()));
                primeiroEmprestimo.setDataDevolucao((emprestimoRequest.dataDevolucao()));
                primeiroEmprestimo.setEstaEmprestado(true);

                //Salvar esse primeiro valor do novo emprestimo
                emprestimoRepository.save(primeiroEmprestimo);
            }
        }

        //Salvar o emprestimo
        emprestimoRepository.save(novoEmprestimo);

        //Retornar o emprestimo
        return novoEmprestimo;
    }

    @Override
    public Emprestimo encontrarEmprestimoPorId(Integer id) {

        return emprestimoRepository.findById(id).orElseThrow(() -> new NoSuchElementException
                ("Nenhum emprestimo foi encontrada com essa id no banco de dados!"));
    }

    @Override
    public List<Emprestimo> encontrarTodosEmprestimos() {

        if(emprestimoRepository.findAll().isEmpty()){
            throw new NoSuchElementException("Nenhum emprestimo encontrado no Banco de dados!");
        }
        return emprestimoRepository.findAll();
    }




    @Override
    public void atualizarEmprestimo(Integer id, EmprestimoRequest emprestimoRequest) {
        Emprestimo emprestimoEncontrada = emprestimoRepository.findById(id).orElseThrow(() -> new
                NoSuchElementException("Nenhum emprestimo encontrado no banco de dados com essa id!"));
        emprestimoEncontrada.setDataEmprestimo((emprestimoRequest.dataEmprestimo()));
        emprestimoEncontrada.setDataDevolucao((emprestimoRequest.dataDevolucao()));
        emprestimoEncontrada.setEstaEmprestado(true); //Continuará emprestado

        //Converter os valores do novo emprestimo para o padrão pt-br
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateTimeFormatter.format(emprestimoEncontrada.getDataEmprestimo());
        dateTimeFormatter.format(emprestimoEncontrada.getDataDevolucao());


        //Verificar se os novos dados serão válidos


        //1. Validar formato da data de Emprestimo, se está a partir do dia atual
        if(emprestimoEncontrada.getDataEmprestimo() == null ||
               emprestimoEncontrada.getDataEmprestimo().isBefore(LocalDate.now()) ||
                !emprestimoEncontrada.getDataEmprestimo().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Por favor, Digite uma data Válida entre o dia atual de hoje!(dia/mes/ano)");
        }

        //2. Validar formato da data de Devolucao, se está do dia atual em diante
        if(emprestimoEncontrada.getDataDevolucao() == null ||
                emprestimoEncontrada.getDataDevolucao().isBefore(emprestimoEncontrada.getDataEmprestimo())){
            throw new IllegalArgumentException("A Data de devolução deve ser maior ou igual a data de emprestimo!");
        }

        //Salvar o novo emprestimo
        emprestimoRepository.save(emprestimoEncontrada);
    }

    @Override
    public void deletarEmprestimo(Integer id) {

        if (!emprestimoRepository.existsById(id)) {
            throw new NoSuchElementException("Nenhum emprestimo encontrado com essa ID no banco de dados!");
        }
        emprestimoRepository.deleteById(id);
        }
    }

