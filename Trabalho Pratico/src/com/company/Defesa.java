package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Defesa extends Jogador implements Serializable {

    private int forca;


    public Defesa(){
        super();
        this.forca = 0;
    }

    public Defesa(String nome, int numero ,int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, int forca,String posicao, ArrayList<String> historial){
        super(nome,numero,velocidade,resistencia,destreza,impulsao,jogoDeCabeca,remate,passe,posicao,historial);
        this.forca = forca;
        this.setHabilidade(this.calculateHability());
    }

    public Defesa(Defesa df){
        super(df);
        this.forca = df.getForca();
    }


    /**
     *  Método de consulta da variável de instância Força
     * @return da variável de instância Força do jogador
     **/
    public int getForca(){
        return this.forca;
    }


    /**
     *  Método de alteração da variável de instância Força
     * @param forca novo valor da variável de instância Força
     **/

    public void setForca(int forca){
        this.forca = forca;
    }



    //Clone da classe Defesa
    /**
     *  Método copia de um Defesa
     * @return Copia do Defesa
     **/
    public Defesa clone() {
        return new Defesa(this);
    }



    //equals
    /**
     *  Método de comparação de um defesa
     * @param o Objeto a comparar com o defesa
     * @return resultado da igualdade
     **/

    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass() != this.getClass())) return false;

        Defesa d = (Defesa) o;

        return (super.equals(d) && this.forca == d.getForca());
    }

    /**
     * Método de comparação de 2 jogadores para ordenação que compara os valores de Habilidade
     * @return subtração do valor de habilidade de um jogador A e um jogador B
     **/
    public int compareTo(Jogador j){
        return super.compareTo(j);
    }

    /**
     *  Método de parse de um Defesa
     * @param input lido do ficheiro, separando os atributos por virgulas
     * @return novo Defesa com os atributos especificados
     **/

    public static Defesa parse(String input){
        String[] campos = input.split(",");
        return new Defesa(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                50,
                "Defesa",
                new ArrayList<>()
                );
    }

    /**
     * @return do valor de Habilidade de um Defesa
     **/
    public int calculateHability(){
        return (int)((double) this.getVelocidade() * 0.10 +
                (double) this.getResistencia() * 0.10 +
                (double) this.getDestreza() * 0.10 +
                (double) this.getImpulsao() * 0.15 +
                (double) this.getJogoDeCabeca() * 0.25 +
                (double) this.getRemate() * 0.05 +
                (double) this.getPasse() * 0.10 +
                (double) this.getForca() * 0.15);

    }

    /**
     * Método de representação das habilidades do Jogador com o atributo especial do defesa sob a forma de uma String
     * @return String com a representação das habilidades do defesa
     **/

    public String toStringHabilidades(){
        return super.toStringHabilidades() +
                "\nForça: "   + this.forca ;
    }


}
