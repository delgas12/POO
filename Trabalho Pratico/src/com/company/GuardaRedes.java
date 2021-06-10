package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class GuardaRedes extends Jogador implements Serializable {

    private int elasticidade;

    //Construtores da classe GuardaRedes

    public GuardaRedes(){
        super();
        this.elasticidade = 0;
    }

    public GuardaRedes(String nome, int numero , int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe,  int elasticidade, String posicao, ArrayList<String> historial) {
        super(nome, numero , velocidade, resistencia, destreza, impulsao, jogoDeCabeca, remate, passe,  posicao, historial);
        this.elasticidade = elasticidade;
        this.setHabilidade(this.calculateHability());
    }

    public GuardaRedes(GuardaRedes j){
        super(j);
        this.elasticidade = j.getElasticidade();
    }

    //Getters

    /**
     *  Método de consulta da variável de instância elasticidade
     * @return valor da variável de instância elasticidade
     **/

    public int getElasticidade() {
        return this.elasticidade;
    }

    //Setters

    /**
     *  Método de alteração da variável de instância elasticidade
     * @param elasticidade novo valor da variável de instância elasticidade
     **/

    public void setElasticidade(int elasticidade) {
        this.elasticidade = elasticidade;
    }

    /**
     *  Método de cópia de um GuardaRedes
     * @return cópia do GuardaRedes sobre o qual foi chamado o método
     **/

    public GuardaRedes clone(){
        return  new GuardaRedes(this);
    }



    /**
     * @return do valor de Habilidade de um Guarda-Redes
     **/
    public int calculateHability(){
            int result = 0;
            result = (int) ((double) this.getVelocidade() * 0.05 +
                            (double) this.getResistencia() * 0.05 +
                            (double) this.getDestreza() * 0.20 +
                            (double) this.getImpulsao() * 0.20 +
                            (double) this.getJogoDeCabeca() * 0.05 +
                            (double) this.getRemate() * 0.10 +
                            (double) this.getPasse() * 0.20 +
                            (double) this.getElasticidade() * 0.15);
            return result;
        }

    /**
     *  Método de parse de um GuardaRedes
     * @param input lido do ficheiro, separando os atributos por virgulas
     * @return novo GuardaRedes com os atributos especificados
     **/

    public static GuardaRedes parse(String input){
        String[] campos = input.split(",");
        return new GuardaRedes  (campos[0],
                Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]),
                "GuardaRedes",
                new ArrayList<>());
    }

    /**
     * Método de representação das habilidades do Jogador com os atributos especiais sob a forma de uma String
     * @return String com a representação das habilidades do GuardaRedes
     **/

    public String toStringHabilidades(){
        return super.toStringHabilidades() +
                "\nElasticidade: "   + this.elasticidade ;
    }
}



