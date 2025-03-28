package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.LeitorResponseDTO;


import java.util.List;

public record LivroComLeitorResponseDTO(Integer id, String nome, Integer anoLancamento,
                                        List<LeitorResponseDTO> listaLeitoresRelacionados) {
}
