package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.associations;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Leitor;

import java.util.List;

public record LivroComLeitorRequestDTO(List<Leitor> listaLeitoresRelacionados) {
}
