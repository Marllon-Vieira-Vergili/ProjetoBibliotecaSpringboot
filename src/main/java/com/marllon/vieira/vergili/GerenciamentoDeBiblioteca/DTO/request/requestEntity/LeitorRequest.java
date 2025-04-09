package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity;
import jakarta.validation.constraints.NotNull;

public record LeitorRequest(
                            String nome,

                            String sobrenome,

                            String email,

                            Integer idade) {
}
