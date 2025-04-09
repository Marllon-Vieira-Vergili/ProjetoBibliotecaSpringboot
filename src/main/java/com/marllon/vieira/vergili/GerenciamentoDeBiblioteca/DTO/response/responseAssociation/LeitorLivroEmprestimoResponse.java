package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseAssociation;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.EmprestimoResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.LeitorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.responseEntity.LivroResponse;

import java.util.List;

public record LeitorLivroEmprestimoResponse(LeitorResponse leitor, List<EmprestimoResponse> emprestimosAssociados,
                                            List<LivroResponse> livrosAssociados) {
}
