package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.AutorRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.AutorResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorServiceImpl implements AutorService{

    @Autowired
    private AutorRepository autorRepository;


    @Override
    public Iterable<AutorResponseDTO> listarTodosOsAutores() {


        return autorRepository.findAll().stream().map(autores -> new AutorResponseDTO(autores.getId(),
                autores.getNome(), autores.getEmail(), autores.getTelefone(), autores.getCidade(),
                autores.getListaLivrosDosAutores())).collect(Collectors.toList());
    }

    @Override
    public Optional<AutorResponseDTO> listarAutorPorId(Integer id) {
        return null;
    }

    @Override
    public AutorRequestDTO salvarAutor(AutorRequestDTO autor) {

     return null;

    }

    @Override
    public void atualizarAutor(Integer id, AutorRequestDTO autor) {

    }

    @Override
    public void deletarAutor(Integer id) {

    }
}
