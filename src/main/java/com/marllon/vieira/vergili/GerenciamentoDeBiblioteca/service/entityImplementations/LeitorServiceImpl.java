package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityImplementations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LeitorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LeitorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces.LeitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LeitorServiceImpl implements LeitorService {

    @Autowired
    private LeitorRepository leitorRepository;


    @Override
    public Leitor criarLeitor(LeitorRequest leitorRequest) {
        //Criar novo leitor
        Leitor novoLeitor = new Leitor();
        novoLeitor.setNome(leitorRequest.nome());
        novoLeitor.setSobrenome(leitorRequest.sobrenome());
        novoLeitor.setIdade(leitorRequest.idade());
        novoLeitor.setEmail(leitorRequest.email());

        //Verificar se o usuário digitou os valores certos para o leitor
        if (novoLeitor.getNome() == null || novoLeitor.getNome().isEmpty() || novoLeitor.getNome().isBlank() || !novoLeitor.getNome()
                .matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("O nome do Leitor deve conter apenas letras e espaços!");
        }

        if (novoLeitor.getEmail() == null || novoLeitor.getEmail().isEmpty() || novoLeitor.getEmail().isBlank() || !novoLeitor.getEmail()
                .matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("O email do autor deve conter @!");
        }

        if (novoLeitor.getSobrenome() == null ||novoLeitor.getSobrenome().isEmpty() || novoLeitor.getSobrenome().isBlank() || !novoLeitor.getSobrenome()
                .matches("^[A-Za-zÀ-ÖØ-öø-ÿ ]+$")) {
            throw new IllegalArgumentException("O sobrenome está incorreto!! deve conter apenas caracteres, espaços permitidos");
        }

        if (novoLeitor.getIdade() == null || novoLeitor.getIdade() < 10 || novoLeitor.getIdade() > 105) {
            throw new IllegalArgumentException("A idade do leitor está incorreta!! idade Mínima para Cadastro: 10 anos, " +
                    "máximo 105 anos");
        }



        //Verificação das lógicas.. verificar se estes leitores criados, já existe
        //Vamos fazer uma condição, se ele encontrar uma id de leitor

        for (Leitor percorrerLeitores : leitorRepository.findAll()) {
            if (percorrerLeitores.getId() == null) {
                Leitor primeiroLeitor = new Leitor();
                primeiroLeitor.setNome(leitorRequest.nome());
                primeiroLeitor.setEmail(leitorRequest.email());
                primeiroLeitor.setSobrenome(leitorRequest.sobrenome());
                primeiroLeitor.setIdade(leitorRequest.idade());
                leitorRepository.save(primeiroLeitor);
            }
            Optional<Leitor> leitorExiste = leitorRepository.findById(percorrerLeitores.getId());
            if (leitorExiste.isPresent()) {
                if (leitorExiste.get().getEmail().equals(novoLeitor.getEmail())) { //Se o email dos 2 forem iguais
                    throw new IllegalArgumentException("Não é possível criar um leitor com um email já existente!");
                }
            }
        }
        //Salvar o leitor
        leitorRepository.save(novoLeitor);

        //Retornar o autor criado
        return novoLeitor;

    }

    @Override
    public Leitor encontrarLeitorPorId(Integer id) {
        return leitorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Não foi encontrado nenhum " +
                "leitor com esse ID no banco de dados!"));
    }

    @Override
    public List<Leitor> encontrarTodosLeitores() {
        if(leitorRepository.findAll().isEmpty()){
            throw new NoSuchElementException("Nenhum Leitor encontrado no Banco de dados!");
        }
        return leitorRepository.findAll();
    }

    @Override
    public List<Leitor> encontrarLeitorPorNome(String nome) {


        //Encontrar os leitores pelo nome
        List<Leitor> listaLeitor = leitorRepository.findByNome(nome);

        //Verificar, quando tiver 2 nomes iguais, ele deverá retornar os 2 resultados, ou mais.
        for(Leitor leitorPercorrido: listaLeitor){
            if(leitorPercorrido.getNome().equals(nome)){
                try{
                    return leitorRepository.findByNome(nome);
                }catch (Exception e){
                    throw new NoSuchElementException("Não foi encontrado nenhum leitor com esse nome no banco de dados!");
                }
            }
        }
        return listaLeitor;
    }

    @Override
    public void atualizarLeitor(Integer id, LeitorRequest leitorRequest) {

       Leitor leitorEncontrado = leitorRepository.findById(id).orElseThrow(() -> new
                NoSuchElementException("Nenhum leitor encontrado com essa id no banco de dados!"));
        leitorEncontrado.setNome(leitorRequest.nome());
        leitorEncontrado.setSobrenome(leitorRequest.sobrenome());
        leitorEncontrado.setEmail(leitorRequest.email());
        leitorEncontrado.setIdade(leitorRequest.idade());
        
        

        //Verificar se os novos dados serão válidos
        if (leitorEncontrado.getNome() == null || leitorEncontrado.getNome().isEmpty() || leitorEncontrado.getNome().isBlank() || !leitorEncontrado.getNome()
                .matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("O nome do Leitor deve conter apenas letras e espaços!");
        }

        if (leitorEncontrado.getEmail() == null || leitorEncontrado.getEmail().isEmpty() || leitorEncontrado.getEmail().isBlank() || !leitorEncontrado.getEmail()
                .matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("O email do autor deve conter @!");
        }

        if (leitorEncontrado.getSobrenome() == null ||leitorEncontrado.getSobrenome().isEmpty() || leitorEncontrado.getSobrenome().isBlank() || !leitorEncontrado.getSobrenome()
                .matches("^[A-Za-zÀ-ÖØ-öø-ÿ ]+$")) {
            throw new IllegalArgumentException("O sobrenome está incorreto!! deve conter apenas caracteres, espaços permitidos");
        }

        if (leitorEncontrado.getIdade() == null || leitorEncontrado.getIdade() < 10 || leitorEncontrado.getIdade() > 105) {
            throw new IllegalArgumentException("A idade do leitor está incorreta!! idade Mínima para Cadastro: " +
                    "10 anos, máximo 105 anos");
        }

        //Salvar o autor
        leitorRepository.save(leitorEncontrado);
    }

    @Override
    public void deletarLeitor(Integer id) {


        if (!leitorRepository.existsById(id)) {
            throw new NoSuchElementException("Nenhum leitor encontrado com essa ID no banco de dados!");
            }
        leitorRepository.deleteById(id);
        }


    @Override
    public Leitor salvarLeitor(Leitor leitor) {
        return leitorRepository.save(leitor);
    }
}

