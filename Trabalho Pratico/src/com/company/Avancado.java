package com.company;

import java.util.ArrayList;

public class Avancado extends Jogador{

    public Avancado(){
        super();
    }

    public Avancado(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, String posicao, ArrayList<String> historial){
        super(nome,velocidade,resistencia,destreza,impulsao,jogoDeCabeca,remate,passe,posicao,historial);
    }

    public Avancado(Avancado a ){
        super(a);
    }

    public int calculateHability(){
       return (int)((double) getVelocidade() * 0.15 +
                (double) this.getResistencia() * 0.15 +
                (double) this.getDestreza() * 0.10 +
                (double) this.getImpulsao() * 0.15 +
                (double) this.getJogoDeCabeca() * 0.15 +
                (double) this.getRemate() * 0.20 +
                (double) this.getPasse() * 0.10);
    }

    public Avancado clone(){
        return new Avancado(this);
    }
}
