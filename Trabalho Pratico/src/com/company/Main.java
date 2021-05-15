package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Main {

        //Jogador(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, String posicao, ArrayList<String> historial) {
    public static void main (String args[]){
        /*
        Parser p;
        try {
            System.out.println("correu");
             p = new Parser();
             p.parse();
             Liga l = new Liga();
             l.setEquipas(p.getEquipas());
             l.setJogos(p.getJogos());
            //System.out.println(l.toString());
            System.out.println(l.getEquipa("Mendelssohn F. C."));
            //System.out.println(l.getJogos());
        }
        catch (LinhaIncorretaException e) {
            System.out.println(e.getMessage());
        }
        Liga l = new Liga();
        */
        String[] s1 = {"Inicio", "Adicionar Equipas" , "adicionar Jogadores"};
        Menu start = new Menu(s1);
        start.execute();
        int op = start.getOption();
        System.out.println(op);


    }
}
