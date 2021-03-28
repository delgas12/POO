package com.company;

import java.util.ArrayList;

public class Equipa {

    private ArrayList<Jogador> titulares;
    private ArrayList<Jogador> suplentes;



    public Equipa(Equipa e){
        this.titulares = new ArrayList<>();
        this.titulares.addAll(e.getTitulares());
        this.suplentes = new ArrayList<>();
        this.suplentes.addAll(e.getTitulares());
    }

    public Equipa(ArrayList<Jogador> titulares, ArrayList<Jogador> suplentes){
        this.titulares = copyList(titulares);
        this.suplentes = copyList(suplentes);
    }

    //Getters

    public ArrayList<Jogador> getTitulares() {
        return copyList(titulares);
    }

    public ArrayList<Jogador> getSuplentes() {

        return copyList(suplentes);
    }


    //Setters

    public void setSuplentes(ArrayList<Jogador> suplentes) {

        this.suplentes = copyList(suplentes);
    }

    public void setTitulares(ArrayList<Jogador> titulares) {
        this.titulares = copyList(titulares);
    }

    public ArrayList<Jogador> copyList(ArrayList <Jogador> jogadores){
        ArrayList<Jogador> newJogadores = new ArrayList<Jogador>(jogadores.size());
        for (Jogador j : jogadores) {
            newJogadores.add(new Jogador(j));
        }
        return newJogadores;
    }


}
