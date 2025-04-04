package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity;


public record LeitorResponse(Integer id, String nome, String sobrenome, String email,
                             Integer idade) {
}
