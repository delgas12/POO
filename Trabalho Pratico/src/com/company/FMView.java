package com.company;

import java.security.Guard;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FMView {


    public static Jogador getPlayer() throws CamposInvalidos{
        Jogador j = null ;
        System.out.println("**** Introduzir Jogador ****");
        System.out.println("Posição,Nome,Numero,Velocidade,Resistencia,Destreza,Impulsao,JogoDeCabeça,Remate,Passe,Atributos Da posição");
        Scanner sc = new Scanner(System.in);
        String[] pos =  sc.nextLine().split(",");
        if(!checkPosition(pos[0].toLowerCase(Locale.ROOT))) throw new CamposInvalidos("Posição Introduzida INVALIDA!");
        switch (pos[0].toLowerCase(Locale.ROOT)){
            case "lateral":
                if(pos.length != 11) throw new CamposInvalidos("Para o Lateral sao necessarios 11 campos separados por virgulas! Sendo os seus atributos [Cruzamento]");
                j = new Lateral(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                                       Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),"Lateral",new ArrayList<>());
                break;
            case "medio":
                if(pos.length != 11) throw new CamposInvalidos("Para o Medio sao necessarios 11 campos separados por virgulas! Sendo os atributos [ReceberBola]");
                j = new Medio(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                        Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),"Lateral",new ArrayList<>());
                break;
            case "avançado":
                if(pos.length != 12) throw new CamposInvalidos("Para o Medio sao necessarios 11 campos separados por virgulas! Sendo os atributos [Agilidade,Finalização]");
                j = new Avancado(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                        Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),Integer.parseInt(pos[11]),"Avançado",new ArrayList<>());
                break;
            case "guardaredes":
                if(pos.length != 11) throw new CamposInvalidos("Para o Medio sao necessarios 11 campos separados por virgulas! Sendo os atributos [Elasticidade]");
                j = new GuardaRedes(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                        Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),"Avançado",new ArrayList<>());
                break;
            case "defesa":
                if(pos.length != 11) throw new CamposInvalidos("Para o Medio sao necessarios 11 campos separados por virgulas! Sendo os atributos [Força]");
                j = new Defesa(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                        Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),"Avançado",new ArrayList<>());
                break;
        }
       return j;
    }

    private static boolean checkPosition(String pos){
        return pos.equals("defesa") || pos.equals("avançado") || pos.equals("lateral") || pos.equals("medio") || pos.equals("guardaredes");
    }

    private static String getDefesaAtributes(String pos){
        Scanner sp = new Scanner(System.in);

        System.out.println("Introduzir atributos do jogador");
        return sp.nextLine();
    }
}
