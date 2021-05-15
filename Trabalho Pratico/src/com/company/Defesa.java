package com.company;

import java.util.ArrayList;

public class Defesa extends Jogador{

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

    public int getForca(){
        return this.forca;
    }

    public void setForca(int forca){
        this.forca = forca;
    }

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

    public Defesa clone() {
        return new Defesa(this);
    }

    //equals

    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass() != this.getClass())) return false;

        Defesa d = (Defesa) o;

        return (super.equals(d) && this.forca == d.getForca());
    }

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

}
