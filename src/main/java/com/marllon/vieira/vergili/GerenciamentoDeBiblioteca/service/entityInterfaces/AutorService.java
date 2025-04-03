package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.service.entityInterfaces;

import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request.AutorRequest;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.response.AutorResponse;
import com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.Autor;

public interface AutorService {


    Autor criarAutor(AutorRequest autorRequest);//associação bidirecional



}
