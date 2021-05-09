package com.company;

import java.util.ArrayList;

public class Avancado extends Jogador{

    private int agilidade;

    private int finalizacao;

    public Avancado(){
        super();
        this.agilidade = 0;
    }

    public Avancado(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, String posicao, ArrayList<String> historial, int agilidade){
        super(nome,velocidade,resistencia,destreza,impulsao,jogoDeCabeca,remate,passe,posicao,historial);
        this.agilidade = agilidade;
        this.setHabilidade(this.calculateHability());
    }

    public Avancado(Avancado a){
        super(a);
        this.agilidade = a.getAgilidade();
    }

    public int getAgilidade(){
        return this.agilidade;
    }

    public void setAgilidade(int agilidade){
        this.agilidade = agilidade;
    }

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

    public Avancado clone(){
        return new Avancado(this);
    }

    //equals

    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass() != this.getClass())) return false;

        Avancado a = (Avancado) o;

        return (super.equals(a) && this.agilidade == a.getAgilidade());
    }
}
