package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Lateral extends Jogador implements Serializable {
    private  int cruzamentos;


    //Construtores da classe Lateral

    public Lateral(){
        super();
        this.cruzamentos = 0;
    }

    public Lateral(String nome, int numero ,int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, int cruzamentos, String posicao, ArrayList<String> historial){
        super(nome,numero,velocidade,resistencia,destreza,impulsao,jogoDeCabeca,remate,passe,posicao,historial);
        this.cruzamentos = cruzamentos;
        this.setHabilidade(this.calculateHability());
    }

    public Lateral(Lateral l){
        super(l);
        this.cruzamentos = l.getCruzamentos();
    }

    /**
     *  Método de consulta do valor da variável de instância Cruzamentos
     * @return valor da variável de instância Cruzamentos do jogador
     **/
    public int getCruzamentos(){
        return this.cruzamentos;
    }


    /**
     *  Método de alteração do variável de instância cruzamentos
     * @param cruzamentos novo valor da variável de instância cruzamentos
     **/
    public void setCruzamentos(int cruzamentos){
        this.cruzamentos = cruzamentos;
    }


    /**
     *  Método cópia de um Avancado
     * @return Cópia do Avancado
     **/
    public Lateral clone(){
        return new Lateral(this);
    }

    /**
     *  Método de comparação de um lateral
     * @param o Objeto a comparar com o lateral
     * @return resultado da igualdade
     **/
    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass() != this.getClass())) return false;

        Lateral l = (Lateral) o;

        return (super.equals(l) && this.cruzamentos == l.getCruzamentos());
    }

    /**
     * Método de comparação de 2 jogadores para ordenação que compara os valores de Habilidade
     * @return subtração do valor de habilidade de um jogador A e um jogador B
     **/
    public int compareTo(Lateral l){
        return super.compareTo(l);
    }


    /**
     *  Método de parse de um Avançado
     * @param input lido do ficheiro, separando os atributos por vírgulas
     * @return novo Avançado com os atributos especificados
     **/
    public static Lateral parse(String input){
        String[] campos = input.split(",");
        return new Lateral(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]),
                "Lateral",
                new ArrayList<>());
    }


    /**
     * @return do valor de Habilidade de um Avancado
     **/
    public int calculateHability(){
        return  (int)((double) this.getVelocidade() * 0.15 +
                (double) this.getResistencia() * 0.10 +
                (double) this.getDestreza() * 0.15 +
                (double) this.getImpulsao() * 0.05 +
                (double) this.getJogoDeCabeca() * 0.05 +
                (double) this.getRemate() * 0.10 +
                (double) this.getPasse() * 0.15 +
                (double) this.getCruzamentos() * 0.15);
    }

    /**
     * Método de representação das habilidades do Jogador com os atributos especiais do Lateral sob a forma de uma String
     * @return String com a representação das habilidades do Lateral
     **/

    public String toStringHabilidades(){
        return super.toStringHabilidades() +
                "\nCruzamentos: "   + this.cruzamentos ;
    }


}
