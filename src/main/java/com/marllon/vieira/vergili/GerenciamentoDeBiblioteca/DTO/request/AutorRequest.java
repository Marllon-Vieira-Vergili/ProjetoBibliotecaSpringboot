package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request;

import java.util.List;

public record AutorRequest(String nome, String email,
                           String telefone, String cidade) {
}
