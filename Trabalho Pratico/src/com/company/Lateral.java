package com.company;

import java.util.ArrayList;

public class Lateral extends Jogador{
    private  int cruzamentos; //?? ou passes_longos ?
    public Lateral(){
        super();
        this.cruzamentos = 0;
    }

    public Lateral(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, String posicao, ArrayList<String> historial, int cruzamentos){
        super(nome,velocidade,resistencia,destreza,impulsao,jogoDeCabeca,remate,passe,posicao,historial);
        this.cruzamentos = cruzamentos;
        this.setHabilidade(this.calculateHability());
    }

    public Lateral(Lateral l){
        super(l);
        this.cruzamentos = l.getCruzamentos();
    }

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

    public int getCruzamentos(){
        return this.cruzamentos;
    }

    public void setCruzamentos(int cruzamentos){
        this.cruzamentos = cruzamentos;
    }

    public Lateral clone(){
        return new Lateral(this);
    }

    //equals

    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass() != this.getClass())) return false;

        Lateral l = (Lateral) o;

        return (super.equals(l) && this.cruzamentos == l.getCruzamentos());
    }

}
