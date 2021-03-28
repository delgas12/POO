package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

        //Jogador(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, String posicao, ArrayList<String> historial) {
    public static void main (String args[]){

            ArrayList<String> histToldy = new ArrayList<>();
            histToldy.add("Tondela");
            histToldy.add("Rio Ave");

            Jogador Toldy = new Jogador("Toldy", 70, 80, 75, 68, 73, 90, 60, "Avançado", histToldy);
            System.out.println("O BOI tem as seguintes caracteristicas:" + Toldy.toString());
    }
}
