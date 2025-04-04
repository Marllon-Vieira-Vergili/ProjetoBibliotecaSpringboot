package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record EmprestimoResponse(Integer EmprestimoId,
       @DateTimeFormat(pattern = "dd/MM/yyyy")
                @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
       @Schema(type = "string", format = "date", example = "10/04/2025")//pro swagger ler o padrao pt-br
        LocalDate dataEmprestimo,
                                 @DateTimeFormat(pattern = "dd/MM/yyyy")
                                 @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
                                 @Schema(type = "string", format = "date", example = "10/04/2025")//pro swagger ler o padrao pt-br
                                 LocalDate dataDevolucao) {
}
