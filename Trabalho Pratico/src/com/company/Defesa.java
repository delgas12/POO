package com.company;

import java.util.ArrayList;

public class Defesa extends Jogador{

    public Defesa(){
        super();
    }

    public Defesa(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, String posicao, ArrayList<String> historial){
        super(nome,velocidade,resistencia,destreza,impulsao,jogoDeCabeca,remate,passe,posicao,historial);

    }

    public Defesa(Defesa df){
        super(df);
    }

    public int calculateHability(){
        return (int)((double) this.getVelocidade() * 0.10 +
                (double) this.getResistencia() * 0.10 +
                (double) this.getDestreza() * 0.10 +
                (double) this.getImpulsao() * 0.20 +
                (double) this.getJogoDeCabeca() * 0.3 +
                (double) this.getRemate() * 0.05 +
                (double) this.getPasse() * 0.15);

    }

    public Defesa clone() {
        return new Defesa(this);
    }

}
