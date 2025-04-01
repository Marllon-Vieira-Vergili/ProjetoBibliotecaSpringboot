package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.associationsImplementations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.AutorELivrosRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations.LivroEAutorRequestDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LivroResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.AutorELivrosResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations.LivroEAutorResponseDTO;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Livro;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.AutorRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.repository.LivroRepository;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.services.interfaces.associationsInterfaces.Autor_Livros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;



@Service
public class Autor_livrosImpl implements Autor_Livros {




    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;



    @Override
    public AutorELivrosResponseDTO associarAutorALivros(AutorELivrosRequestDTO autorELivrosRequestDTO) {

        //Verificar se o autor existe
        Autor autorEncontrado = autorRepository.findById(autorELivrosRequestDTO.autorId())
                .orElseThrow(() -> new NoSuchElementException("Nenhum autor foi encontrado!"));

        //Verificar se o livro existe
        List <Livro> livrosEncontrados = livroRepository.findAllById(autorELivrosRequestDTO.livrosIds());
        if(livrosEncontrados.isEmpty()){
            throw new NoSuchElementException("Nenhum livro foi encontrado nessa lista!");
        }

        //associar os livros para o autor
        for(Livro livros: livrosEncontrados){
            livros.setAutorRelacionado(autorEncontrado);
            autorEncontrado.getListaLivrosDosAutores().add(livros);
        }


        //Salvar o autor relacionado ao livro
        autorRepository.save(autorEncontrado);



        //Mapear os valores da Entidade Livro, para converter no formato de DTO
        List<LivroResponseDTO> listaLivroConvertido = livrosEncontrados.stream()
                .map(livro -> new LivroResponseDTO(livro.getId(),
                                livro.getNome(), livro.getAnoLancamento())).toList();



        //Retornar o retorno do autor e seus livros que foram relacionados a ele
        return new AutorELivrosResponseDTO(autorEncontrado.getId(),
                autorEncontrado.getNome(),
                autorEncontrado.getEmail(), autorEncontrado.getCidade(), autorEncontrado.getTelefone(),
                listaLivroConvertido);
    }


    @Override
    public List<LivroEAutorResponseDTO> associarLivrosAUmAutor(LivroEAutorRequestDTO livroEAutorRequestDTO) {

        //Verificar se o livro existe
        List<Livro> livrosEncontrados = livroRepository.findAllById(livroEAutorRequestDTO.livrosIds());

        //Verificar se o autor existe
        Autor autorEncontrado = autorRepository.findById(livroEAutorRequestDTO.IdAutorRelacionado())
                .orElseThrow(() -> new NoSuchElementException("Nenhum autor encontrado com essa id!"));

        //associar cada livro que for encontrado, e setar o autor relacionado de todos eles
        livrosEncontrados.forEach(livro -> livro.setAutorRelacionado(autorEncontrado));



            //Salvar o livro para o autor que será associado
            livroRepository.saveAll(livrosEncontrados);
            

        //Retornar a lista de livros e seu autor associado a estes livros

        return livrosEncontrados.stream()
                .map(livro -> new LivroEAutorResponseDTO(
                        livro.getId(),
                        livro.getNome(),
                        livro.getAnoLancamento(),
                        autorEncontrado.getId(),
                    autorEncontrado.getNome(),
                    autorEncontrado.getEmail())).toList();

    }

    @Override
    public List<AutorELivrosResponseDTO> encontrarAutorESeusLivrosAssociados (Integer id) {

        //Encontrar o autor pela id
        Autor autorEncontrado = autorRepository.findById(id).orElseThrow(() -> new
                NoSuchElementException("Autor não encontrado com essa id!"));

        if(autorEncontrado.getListaLivrosDosAutores().isEmpty()){
            throw new NoSuchElementException("Este autor não possui nenhum livro associado a ele");
        }

        //Mapear os livros encontrados para converter ao DTO
        List<LivroResponseDTO> livrosDoAutor = autorEncontrado.getListaLivrosDosAutores()
                .stream().map(livro -> new LivroResponseDTO(livro.getId(),
                        livro.getNome(),livro.getAnoLancamento())).toList();


        //Retornar a lista com todos os dados do autor, e sua lista de livros
        return List.of(new AutorELivrosResponseDTO(autorEncontrado.getId(), autorEncontrado.getNome(),
                autorEncontrado.getEmail(), autorEncontrado.getTelefone(), autorEncontrado.getCidade(),
                livrosDoAutor));
    }
}
