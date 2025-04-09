package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestAssociation;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.EmprestimoRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LeitorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.requestEntity.LivroRequest;


public record LeitorLivroEmprestimoRequest(LivroRequest livroRequest, LeitorRequest leitorRequest,
                                           EmprestimoRequest emprestimoRequest) {
}
