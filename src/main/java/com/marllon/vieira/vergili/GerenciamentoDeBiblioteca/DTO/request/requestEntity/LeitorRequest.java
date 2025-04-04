package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity;
import jakarta.validation.constraints.NotNull;

public record LeitorRequest(@NotNull(message = "Nome do leitor é obrigatório!")
                            String nome,
                            @NotNull(message = "Sobrenome do leitor é obrigatório!")
                            String sobrenome,
                            @NotNull(message = "Email do leitor é obrigatório!")
                            String email,
                            @NotNull(message = "Idade do leitor é obrigatório!")
                            Integer idade) {
}
