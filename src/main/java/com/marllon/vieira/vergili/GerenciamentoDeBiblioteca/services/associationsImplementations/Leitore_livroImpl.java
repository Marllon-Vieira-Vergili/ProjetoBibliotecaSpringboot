package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.associationsImplementations;


import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LeitorComLivroRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroComLeitorRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LeitorResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LeitorComLivroResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroComLeitorResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LeitorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LivroRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces.Leitores_Livros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class Leitore_livroImpl implements Leitores_Livros {


    @Autowired
    private LeitorRepository leitorRepository;

    @Autowired
    private LivroRepository livroRepository;


    @Override
    public LeitorComLivroResponseDTO associarLeitorAUmLivro(LeitorComLivroRequestDTO leitorComLivroRequestDTO) {
        //Encontrar leitor
        Leitor leitorEncontrado = leitorRepository.findById(leitorComLivroRequestDTO.leitorId()).orElseThrow(() ->
                new NoSuchElementException("Nenhum leitor encontrado com essa id!"));

        //encontrar livro
        Livro livroEncotrado = livroRepository.findById(leitorComLivroRequestDTO.livroId()).orElseThrow(() ->
                new NoSuchElementException("Nenhum livro encontrado com essa id!"));

        //Associar leitor ao livro
        leitorEncontrado.associarLeitorALivro(livroEncotrado);

        //Salvar leitor
        leitorRepository.save(leitorEncontrado);

        //Converter os dados encontrados em uma lista de DTO
        List<LivroResponseDTO> livroResponseDTOS = Collections.singletonList(new LivroResponseDTO(livroEncotrado.getId(),
                livroEncotrado.getNome(), livroEncotrado.getAnoLancamento()));


        //Retornar ao usuário, o que ele irá ver

        return new LeitorComLivroResponseDTO(leitorEncontrado.getId(), leitorEncontrado.getNome(),
                leitorEncontrado.getSobrenome(), leitorEncontrado.getEmail(),
                leitorEncontrado.getIdade(), livroResponseDTOS);
    }

    @Override
    public LivroComLeitorResponseDTO associarLivroAUmLeitor(LivroComLeitorRequestDTO livroComLeitorRequestDTO) {

        //Encontrar leitor
        Leitor leitorEncontrado = leitorRepository.findById(livroComLeitorRequestDTO.leitorId()).orElseThrow(() ->
                new NoSuchElementException("Nenhum leitor encontrado com essa id!"));

        //encontrar livro
        Livro livroEncotrado = livroRepository.findById(livroComLeitorRequestDTO.livroId()).orElseThrow(() ->
                new NoSuchElementException("Nenhum livro encontrado com essa id!"));

        //associar livro ao leitor
        livroEncotrado.associarLivroALeitor(leitorEncontrado);

        //Salvar o livro
        livroRepository.save(livroEncotrado);

        //Converter a lista de leitores para DTO
        List<LeitorResponseDTO> leitorResponseDTOS = Collections.singletonList(new LeitorResponseDTO
                (leitorEncontrado.getId(), leitorEncontrado.getNome(), leitorEncontrado.getSobrenome(),
                        leitorEncontrado.getEmail(), leitorEncontrado.getIdade()));

        //Retornar os dados de json para o usuário analisar, em formato do DTO
        return new LivroComLeitorResponseDTO(livroEncotrado.getId(), livroEncotrado.getNome(),
                livroEncotrado.getAnoLancamento(),leitorResponseDTOS);
    }

    @Override
    public LeitorComLivroResponseDTO encontrarLeitorComLivroRelacionado(Integer id) {

        //Encontrar leitor
        Leitor leitorEncontrado = leitorRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum leitor encontrado com essa id!"));

        //encontrar livro
        Livro livroEncotrado = livroRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum livro encontrado com essa id!"));

        //Converter para DTO a lista de livro
        List<LivroResponseDTO> livroResponseDTOS  = Collections.singletonList(new
                LivroResponseDTO(livroEncotrado.getId(), livroEncotrado.getNome(), livroEncotrado.getAnoLancamento()));

        //Retornar o formato da DTO
        return new LeitorComLivroResponseDTO(leitorEncontrado.getId(), leitorEncontrado.getNome(),
                leitorEncontrado.getSobrenome(), leitorEncontrado.getEmail(),
                leitorEncontrado.getIdade(), livroResponseDTOS);
    }
}
