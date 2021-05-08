package com.company;

import java.util.ArrayList;

public class Medio extends Jogador{

    public Medio(){
        super();
    }

    public Medio(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, String posicao, ArrayList<String> historial){
      super(nome,velocidade,resistencia,destreza,impulsao,jogoDeCabeca,remate,passe,posicao,historial);
    }

    public Medio(Medio m){
        super(m);
    }

    public int calculateHability(){
        return (int)((double) this.getVelocidade() * 0.10 +
                (double) this.getResistencia() * 0.15 +
                (double) this.getDestreza() * 0.20 +
                (double) this.getImpulsao() * 0.05 +
                (double) this.getJogoDeCabeca() * 0.05 +
                (double) this.getRemate() * 0.15 +
                (double) this.getPasse() * 0.20);
    }

    public Medio clone(){
        return new Medio(this);
    }


}
