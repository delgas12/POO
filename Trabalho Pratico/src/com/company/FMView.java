package com.company;

import java.security.Guard;
import java.util.*;

public class FMView {


    public static Map.Entry<String,Jogador> getPlayer() throws CamposInvalidos{
        Jogador j = null ;
        System.out.println("**** Introduzir Jogador ****");
        System.out.println("Posição,Nome,Numero,Velocidade,Resistencia,Destreza,Impulsao,JogoDeCabeça,Remate,Passe,Atributos Da posição,Equipa");
        Scanner sc = new Scanner(System.in);
        String[] pos =  sc.nextLine().split(",");
        String equipa = "";
        if(!checkPosition(pos[0].toLowerCase(Locale.ROOT))) throw new CamposInvalidos("Posição Introduzida INVALIDA!");
        switch (pos[0].toLowerCase(Locale.ROOT)){
            case "lateral":
                if(pos.length != 12) throw new CamposInvalidos("Para o Lateral sao necessarios 11 campos separados por virgulas! Sendo os seus atributos [Cruzamento]");
                j = new Lateral(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                                       Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),"Lateral",new ArrayList<>());
                equipa = pos[11];
                break;
            case "medio":
                if(pos.length != 12) throw new CamposInvalidos("Para o Medio sao necessarios 11 campos separados por virgulas! Sendo os atributos [ReceberBola]");

                j = new Medio(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                        Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),"Medio",new ArrayList<>());
                equipa = pos[11];
                break;
            case "avançado":
                if(pos.length != 13) throw new CamposInvalidos("Para o Avançado sao necessarios 12 campos separados por virgulas! Sendo os atributos [Agilidade,Finalização]");
                j = new Avancado(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                        Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),Integer.parseInt(pos[11]),"Avançado",new ArrayList<>());
                equipa = pos[12];
                break;
            case "guardaredes":
                if(pos.length != 12) throw new CamposInvalidos("Para o Medio sao necessarios 11 campos separados por virgulas! Sendo os atributos [Elasticidade]");
                j = new GuardaRedes(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                        Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),"GuardaRedes",new ArrayList<>());
                equipa = pos[11];
                break;
            case "defesa":
                if(pos.length != 12) throw new CamposInvalidos("Para o Medio sao necessarios 11 campos separados por virgulas! Sendo os atributos [Força]");
                j = new Defesa(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                        Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),"Defesa",new ArrayList<>());
                equipa = pos[11];
                break;
        }
       return new AbstractMap.SimpleEntry(equipa, j);
    }

    private static boolean checkPosition(String pos){
        return pos.equals("defesa") || pos.equals("avançado") || pos.equals("lateral") || pos.equals("medio") || pos.equals("guardaredes");
    }

    private static String getDefesaAtributes(String pos){
        Scanner sp = new Scanner(System.in);

        System.out.println("Introduzir atributos do jogador");
        return sp.nextLine();
    }

    public static String getEquipa(){
        System.out.print("Insira o nome da equipa que pretende adicionar:");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static Map.Entry<String, Map.Entry<String, String>> getTransferencia(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o nome da equipa origem: ");
        String equipaOrigem = sc.nextLine();
        System.out.print("Insira o nome do jogador: ");
        String jogador = sc.nextLine();
        System.out.print("Insira o nome da equipa destino: ");
        String equipaDestino = sc.nextLine();
        return new AbstractMap.SimpleEntry(jogador, new AbstractMap.SimpleEntry(equipaOrigem, equipaDestino));
    }

    public static String consultarEquipa(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o nome da equipa a consultar: ");
        return sc.nextLine();
    }

    public static void displayEquipa(Equipa e){
        System.out.println(e.toString());
    }

    public static String consultarJogador(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o nome do jogador a consultar: ");
        return sc.nextLine();
    }

    public static void displayJogador(Jogador j){
        System.out.println(j.toString());
    }
}
