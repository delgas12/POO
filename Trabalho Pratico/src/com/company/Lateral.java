package com.company;

import java.util.ArrayList;

public class Lateral extends Jogador{
    private  int cruzamentos; //?? ou passes_longos ?
    public Lateral(){
        super();
    }

    public Lateral(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, String posicao, ArrayList<String> historial){
        super(nome,velocidade,resistencia,destreza,impulsao,jogoDeCabeca,remate,passe,posicao,historial);
    }

    public Lateral(Lateral l){
        super(l);
    }

    public int calculateHability(){
        return  (int)((double) this.getVelocidade() * 0.10 +
                (double) this.getResistencia() * 0.15 +
                (double) this.getDestreza() * 0.20 +
                (double) this.getImpulsao() * 0.05 +
                (double) this.getJogoDeCabeca() * 0.05 +
                (double) this.getRemate() * 0.15 +
                (double) this.getPasse() * 0.20);
    }

    public Lateral clone(){
        return new Lateral(this);
    }
}
