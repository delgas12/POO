package com.company;

import java.util.ArrayList;

public class Medio extends Jogador{

    private int recBola;

    public Medio(){
        super();
        this.recBola = 0;
    }

    public Medio(String nome, int numero ,int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, int recBola, String posicao, ArrayList<String> historial){
        super(nome, numero ,velocidade,resistencia,destreza,impulsao,jogoDeCabeca,remate,passe,posicao,historial);
        this.recBola = recBola;
        this.setHabilidade(this.calculateHability());
    }

    public Medio(Medio m){
        super(m);
        this.recBola = m.getRecBola();
    }

    public int getRecBola(){
        return this.recBola;
    }

    public void setRecBola(int recBola){
        this.recBola = recBola;
    }

    public int calculateHability(){
        return (int)((double) this.getVelocidade() * 0.10 +
                (double) this.getResistencia() * 0.15 +
                (double) this.getDestreza() * 0.20 +
                (double) this.getImpulsao() * 0.02 +
                (double) this.getJogoDeCabeca() * 0.03 +
                (double) this.getRemate() * 0.15 +
                (double) this.getPasse() * 0.20 +
                (double) this.getRecBola() * 0.15);
    }

    public Medio clone(){
        return new Medio(this);
    }

    //equals

    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass() != this.getClass())) return false;

        Medio m = (Medio) o;

        return (super.equals(m) && this.recBola == m.getRecBola());
    }

    public static Medio parse(String input){
        String[] campos = input.split(",");
        return new Medio(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]),
                "Medio",
                new ArrayList<>());
    }


}
