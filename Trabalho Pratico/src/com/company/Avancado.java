package com.company;

import java.util.ArrayList;

public class Avancado extends Jogador{

    private int agilidade;
    private int finalizacao;

    //Construtores da Classe Avancado

    public Avancado(){
        super();
        this.agilidade = 0;
    }

    public Avancado(String nome, int numero, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, int agilidade, int finalizacao,String posicao, ArrayList<String> historial){
        super(nome,numero, velocidade,resistencia,destreza,impulsao,jogoDeCabeca,remate,passe,posicao,historial);
        this.agilidade = agilidade;
        this.finalizacao = finalizacao;
        this.setHabilidade(this.calculateHability());
    }

    public Avancado(Avancado a){
        super(a);
        this.finalizacao = a.getFinalizacao();
        this.agilidade = a.getAgilidade();
    }


    //Getters e setters da classe Avancado

    /**
     *  Método de consulta do valor da variável de instância finalização
     * @return valor da variável de instância Finalização do jogador
     **/

    public int getFinalizacao(){return this.finalizacao;}

    /**
     *  Método de consulta do valor do variável de instância agilidade
     * @return valor da variável de instância Agilidade do jogador
     **/

    public int getAgilidade(){
        return this.agilidade;
    }

    //Setters

    /**
     *  Método de alteração do variável de instância agilidade
     * @param agilidade novo valor da variável de instância agilidade
     **/

    public void setAgilidade(int agilidade){
        this.agilidade = agilidade;
    }

    /**
     *  Método de alteração da variável de instância finalização
     * @param finalizacao novo valor da variável de instância agilidade
     **/

    public void setFinalizacao(int finalizacao){this.finalizacao = finalizacao;}



    /**
     *  Método cópia de um Avancado
     * @return Cópia do Avancado
     **/

    public Avancado clone(){
        return new Avancado(this);
    }



    /**
     *  Método de comparação de um avançado
     * @param o Objeto a comparar com o avancado
     * @return resultado da igualdade
     **/

    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass() != this.getClass())) return false;

        Avancado a = (Avancado) o;

        return (super.equals(a) && this.agilidade == a.getAgilidade());
    }

    //compareTo


    /**
     * Método de comparação de 2 jogadores para ordenação que compara os valores de Habilidade
     * @return subtração do valor de habilidade de um jogador A e um jogador B
     **/
    public int compareTo(Jogador j){
        return super.compareTo(j);
    }





    /**
     *  Método de parse de um Avançado
     * @param input lido do ficheiro, separando os atributos por vírgulas
     * @return novo Avançado com os atributos especificados
     **/

    public static Avancado parse(String input){
        String[] campos = input.split(",");
        return new Avancado(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                50,
                50,
                "Avancado",
                new ArrayList<>()
        );
    }

    /**
     * @return do valor de Habilidade de um Avancado
     **/
    public int calculateHability(){
        return (int)((double) getVelocidade() * 0.15 +
                (double) this.getResistencia() * 0.10 +
                (double) this.getDestreza() * 0.10 +
                (double) this.getImpulsao() * 0.10 +
                (double) this.getJogoDeCabeca() * 0.15 +
                (double) this.getRemate() * 0.20 +
                (double) this.getPasse() * 0.10 +
                (double) this.getAgilidade() * 0.10);
    }

}
