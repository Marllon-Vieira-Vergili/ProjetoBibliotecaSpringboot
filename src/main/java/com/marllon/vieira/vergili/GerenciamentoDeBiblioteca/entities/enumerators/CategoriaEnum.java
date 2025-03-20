package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.entities.enumerators;

public enum CategoriaEnum {

    ACAO(1),
    AVENTURA(2),
    TERROR(3),
    ROMANCE(4),
    FICCAO(5);

    int valor;

    CategoriaEnum(int valor){
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }


    //método que retorna um valor estático
    public static CategoriaEnum selecionarCategoriaPeloValor(int numero){

        for(CategoriaEnum categoria : CategoriaEnum.values()){
            if (categoria.getValor() == numero){
                return categoria;
            }
        }
        throw new IllegalArgumentException("CategoriaEnum não encontrada!");
    }
}
