package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;



public record EmprestimoRequest(@NotNull(message = "Por favor, informe uma data!")
                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                                //Já formatando o json para o formato string, de dia, mes e ano
                                @DateTimeFormat(pattern = "dd/MM/yyyy")
                                @Schema(type = "string", format = "date", example = "10/04/2025") //Pro swagger ler o padrao
                                LocalDate dataEmprestimo,
                                @NotNull(message = "Por favor, informe uma data!")
                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                                //Já formatando o json para o formato string, de dia, mes e ano
                                @DateTimeFormat(pattern = "dd/MM/yyyy")
                                @Schema(type = "string", format = "date", example = "10/04/2025") //pro swagger ler o padrao
                                LocalDate dataDevolucao) {


}


