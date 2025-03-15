package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities;

public enum Categoria {

    ACAO(1),
    AVENTURA(2),
    TERROR(3),
    ROMANCE(4),
    FICCAO(5);

    int valor;

    Categoria(int valor){
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }


    //método que retorna um valor estático
    public static Categoria selecionarCategoriaPeloValor(int numero){

        for(Categoria categoria : Categoria.values()){
            if (categoria.getValor() == numero){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria não encontrada!");
    }
}
