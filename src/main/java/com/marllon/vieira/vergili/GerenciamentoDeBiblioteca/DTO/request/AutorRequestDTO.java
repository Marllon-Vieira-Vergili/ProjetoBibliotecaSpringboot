package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.DTO.request;

//Classse Record para enviar dados de requisição, por exemplo, seus atributos que o usuário irá inserir no json


public record AutorRequestDTO (String nome, String email, String telefone, String cidade) {


}
