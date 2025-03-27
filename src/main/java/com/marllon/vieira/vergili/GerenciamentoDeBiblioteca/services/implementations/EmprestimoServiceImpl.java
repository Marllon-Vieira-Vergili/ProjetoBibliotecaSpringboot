package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.implementations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.EmprestimoRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.EmprestimoResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Emprestimo;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.EmprestimoRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
public class EmprestimoServiceImpl implements EmprestimoService {


    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Override
    public Iterable<EmprestimoResponseDTO> listarTodosOsEmprestimos() {

        return emprestimoRepository.findAll().stream().map(emprestimo -> new
                EmprestimoResponseDTO(emprestimo.getId(),emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao(), emprestimo.isEstaEmprestado(),
                emprestimo.getListaLivrosEmprestados(),emprestimo.getListaLeitoresComEmprestimos())).toList();

    }

    @Override
    public EmprestimoResponseDTO encontrarEmprestimoPorId(Integer id) {

        Emprestimo emprestimoEncontrado = emprestimoRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum empréstimo encontrado com essa id!"));

        return new EmprestimoResponseDTO(emprestimoEncontrado.getId(),
                emprestimoEncontrado.getDataEmprestimo(), emprestimoEncontrado.getDataDevolucao(),
                emprestimoEncontrado.isEstaEmprestado(), emprestimoEncontrado.getListaLivrosEmprestados(),
                emprestimoEncontrado.getListaLeitoresComEmprestimos());
    }

    @Override
    public EmprestimoResponseDTO salvarEmprestimo(EmprestimoRequestDTO emprestimo) {

        //Instanciar um novo Empréstimo
        Emprestimo novoEmprestimo = new Emprestimo(emprestimo.dataEmprestimo(), emprestimo.dataDevolucao());

        //Salvar o novo empréstimo no banco de dados
        emprestimoRepository.save(novoEmprestimo);

        //Retornar o novo empréstimo salvo para o usuário
        return new EmprestimoResponseDTO(novoEmprestimo.getId(), novoEmprestimo.getDataEmprestimo(),
                novoEmprestimo.getDataDevolucao(), novoEmprestimo.isEstaEmprestado(),
                novoEmprestimo.getListaLivrosEmprestados(), novoEmprestimo.getListaLeitoresComEmprestimos());
    }

    @Override
    public EmprestimoResponseDTO atualizarEmprestimo(Integer id, EmprestimoRequestDTO emprestimoRequestDTO) {

        //Encontrar o emprestimo que quero atualizar
        Emprestimo emprestimoEncontrado = emprestimoRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum empréstimo localizado com essa id!"));

        emprestimoEncontrado.setDataEmprestimo(emprestimoRequestDTO.dataEmprestimo());
        emprestimoEncontrado.setDataDevolucao(emprestimoRequestDTO.dataDevolucao());
        emprestimoEncontrado.setEstaEmprestado(emprestimoEncontrado.isEstaEmprestado());

        //Salvar o novo empréstimo
        emprestimoRepository.save(emprestimoEncontrado);

        return new EmprestimoResponseDTO(emprestimoEncontrado.getId(),
                emprestimoEncontrado.getDataEmprestimo(), emprestimoEncontrado.getDataDevolucao(),
                emprestimoEncontrado.isEstaEmprestado(), emprestimoEncontrado.getListaLivrosEmprestados(),
                emprestimoEncontrado.getListaLeitoresComEmprestimos());

    }

    @Override
    public void removerEmprestimo(Integer id) {

        Emprestimo emprestimoSerRemovido = emprestimoRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhuma empréstimo encontrado com essa ID!"));
        emprestimoRepository.delete(emprestimoSerRemovido);
    }
}
