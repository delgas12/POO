package com.company;

import java.util.ArrayList;

public class Lateral extends Jogador{
    private  int cruzamentos; //?? ou passes_longos ?
    public Lateral(){
        super();
        this.cruzamentos = 0;
    }

    public Lateral(String nome, int numero ,int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, int cruzamentos, String posicao, ArrayList<String> historial){
        super(nome,numero,velocidade,resistencia,destreza,impulsao,jogoDeCabeca,remate,passe,posicao,historial);
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

    public static Lateral parse(String input){
        String[] campos = input.split(",");
        return new Lateral(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]),
                "Lateral",
                new ArrayList<>());
    }

    public int compareTo(Lateral l){
        return super.compareTo(l);
    }


}
