package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.implementations;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.LeitorRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LeitorResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LeitorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.LeitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LeitorServiceImpl implements LeitorService {


    @Autowired
    private LeitorRepository leitorRepository;

    @Override
    public Iterable<LeitorResponseDTO> listarTodosOsLeitores() {
        //Retornar o leitor.encontrar todos, dividindo, em um mapa de leitor, que esse novo leitor vai receber um dto, para lista
        return leitorRepository.findAll().stream().map(leitor -> new LeitorResponseDTO
                (leitor.getId(), leitor.getNome(),leitor.getSobrenome(), leitor.getEmail(), leitor.getIdade(),
                        leitor.getListaLivrosRelacionadosAoLeitor(),
                        leitor.getListaEmprestimosRelacionadosAoLeitor())).toList();
    }

    @Override
    public LeitorResponseDTO listarLeitorPorId(Integer id) {

        Leitor leitorEncontrado = leitorRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum leitor encontrado com essa id!"));
        return new LeitorResponseDTO(leitorEncontrado.getId(), leitorEncontrado.getNome(),
                leitorEncontrado.getSobrenome(), leitorEncontrado.getEmail(), leitorEncontrado.getIdade(),
                leitorEncontrado.getListaLivrosRelacionadosAoLeitor(),
                leitorEncontrado.getListaEmprestimosRelacionadosAoLeitor());
    }

    @Override
    public LeitorResponseDTO salvarLeitor(LeitorRequestDTO requestDTO) {

        //Instanciar um novo Leitor
        Leitor novoLeitor = new Leitor(requestDTO.nome(),
                requestDTO.sobrenome(), requestDTO.email(), requestDTO.idade());

        //Salvar o novo leitor
        leitorRepository.save(novoLeitor);

        //Retornar o novo leitor para o usuário pelo leitorDTO
        return new LeitorResponseDTO(novoLeitor.getId(), novoLeitor.getNome(),
                novoLeitor.getSobrenome(), novoLeitor.getEmail(), novoLeitor.getIdade(),
                novoLeitor.getListaLivrosRelacionadosAoLeitor(), novoLeitor.getListaEmprestimosRelacionadosAoLeitor());
    }

    @Override
    public LeitorResponseDTO atualizarLeitor(Integer id, LeitorRequestDTO leitorRequestDTO) {

        //Encontrar o leitor pela ID
        Leitor leitorASerAtualizado = leitorRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum leitor encontrado com essa id!"));

        //Alterar os dados do leitor encontrado
        leitorASerAtualizado.setNome(leitorRequestDTO.nome());
        leitorASerAtualizado.setSobrenome(leitorRequestDTO.sobrenome());
        leitorASerAtualizado.setEmail(leitorRequestDTO.email());
        leitorASerAtualizado.setIdade(leitorRequestDTO.idade());

        //Salvar as alterações realizadas
        leitorRepository.save(leitorASerAtualizado);

        //Retornar em uma nova resposta pelo DTO, o cabeçalho dos dados atualizado do leitor
        return new LeitorResponseDTO(leitorASerAtualizado.getId(), leitorASerAtualizado.getNome(),
                leitorASerAtualizado.getSobrenome(), leitorASerAtualizado.getEmail(),
                leitorASerAtualizado.getIdade(),  leitorASerAtualizado.getListaLivrosRelacionadosAoLeitor(),
                leitorASerAtualizado.getListaEmprestimosRelacionadosAoLeitor());
    }

    @Override
    public void removerLeitor(Integer id) {

        Leitor leitorSerRemovido = leitorRepository.findById(id).orElseThrow(() -> new
                NoSuchElementException("Nenhum leitor encontrado com essa id!"));

        leitorRepository.delete(leitorSerRemovido);
    }
}
