package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.associations;


public record LivroEAutorResponseDTO(Integer livroId, String livroNome,
                                     Integer anoLancamento, Integer autorId,
                                     String autorNome, String autorEmail) {
}
