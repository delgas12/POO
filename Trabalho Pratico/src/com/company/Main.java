package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Main {

        //Jogador(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, String posicao, ArrayList<String> historial) {
    public static void main (String args[]){
/*
        ArrayList<String> histToldy = new ArrayList<>();
        histToldy.add("Toldyela");
        histToldy.add("TONDELA CARALHO");
        Jogador ToldyDefesa = new Medio("ToldyDefesa", 70, 80, 75, 68, 73, 90, 60, "Defesa", histToldy);
        Jogador ToldyMedio = new Avancado("ToldyMedio", 70, 80, 100, 68, 73, 90, 100, "Médio", histToldy);
        Jogador ToldyAvancado = new Lateral("boi", 70, 80, 100, 68, 73, 100, 60, "Avançado", histToldy);
        GuardaRedes ToldyGR = new GuardaRedes("ToldyGR", 10, 15, 70, 85, 69, 75, 90, "GuardaRedes", histToldy, 100);
        ArrayList<Jogador> titulares = new ArrayList<>();
        titulares.add(ToldyDefesa);
        titulares.add(ToldyMedio);
        titulares.add(ToldyAvancado);
        titulares.add(ToldyGR);
        ArrayList<Jogador> rocaBanco = new ArrayList<>();
        */
        /*
        rocaBanco.add(ToldyDefesa);
        rocaBanco.add(ToldyMedio);
        rocaBanco.add(ToldyAvancado);
        rocaBanco.add(ToldyGR);

         */
        /*
        Equipa osBois = new Equipa("Equip1",titulares,rocaBanco);
        System.out.println("O TOLDY defesa tem as seguintes habilidade: " + ToldyDefesa.calculateHability());
        System.out.println("O TOLDY medio tem as seguintes habilidade: " + ToldyMedio.calculateHability());
        System.out.println("O TOLDY avançado tem as seguintes habilidade: " + ToldyAvancado.calculateHability());
        System.out.println("O TOLDY guarda redes tem as seguindtes habilidades: " + ToldyGR.calculateHability());
        System.out.println("Habilididade dos bois: " + osBois.habilidadeEquipa());

        System.out.println("Encontrou o jogador ? :" + osBois.encontraJogador("ToldyDefesa"));
        System.out.println("Encontrou o jogador ? :" + osBois.encontraJogador("boi"));


        ArrayList<String> histToldy2 = new ArrayList<>();
        histToldy2.add("Toldyela");
        histToldy2.add("TONDELA CARALHO");
        Jogador CasticoDefesa = new Medio("CasticoDefesa", 70, 80, 75, 68, 73, 90, 60, "Defesa", histToldy);
        Jogador CasticoMedio = new Lateral("CasticoMedio", 70, 80, 100, 68, 73, 90, 100, "Médio", histToldy);
        Jogador CasticoAvancado = new Avancado("boiCastico", 70, 80, 100, 68, 73, 100, 60, "Avançado", histToldy);
        GuardaRedes CasticoGR = new GuardaRedes("CasticoGR", 10, 15, 70, 85, 69, 75, 90, "GuardaRedes", histToldy, 100);
        ArrayList<Jogador> titularesCastico = new ArrayList<>();
        titularesCastico.add(CasticoDefesa);
        titularesCastico.add(CasticoMedio);
        titularesCastico.add(CasticoAvancado);
        titularesCastico.add(CasticoGR);
        ArrayList<Jogador> rocaBancoCastico = new ArrayList<>();

        Equipa osBois2 = new Equipa("Equip2",titularesCastico,rocaBancoCastico);


        TreeMap<String,Equipa> equipas = new TreeMap<>();
        equipas.put(osBois.getName(),osBois.clone());
        equipas.put(osBois2.getName(),osBois2.clone());
        Liga potas = new Liga (equipas, 2);



        System.out.println("Liga dos Bois: \n" + potas);
        System.out.println(potas.equipasToString());
        System.out.println("\n\n\n\n\n");

        System.out.println("Transferiu? " + potas.transferencia(osBois2, "ToldyDefesa"));

        //System.out.println("Transferiancia teve sucesso? " + potas.transferencia(osBois2, "ToldyDefesa"));

        System.out.println("\n\n\n\n\n");


        System.out.println("Liga dos Bois: \n" + potas.getEquipasList().toString());
        /*
        System.out.println("A liga potas " + potas.toString());
        potas.transferencia(osBois, "ToldyDefesa");
        System.out.println("\n\nA liga potas a seguir " + potas.toString());
         */



    }
}
