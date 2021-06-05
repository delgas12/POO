package com.company;

import java.util.Arrays;
import java.util.Map;

// Wagner Athletic
// Sporting Club Schubert

//Avançado,Jucca,13,100,100,100,100,100,100,100,100,100,
//Defesa,Toldy,13,100,100,100,100,100,100,100,100,100
//Lateral,Castiçu,13,100,100,100,100,100,100,100,100,100

// David Alexandre Ferreira Duarte
// Tiago Lucas Alves

public class FMApp {
    public static void main(String[] args){

        Liga l = new Liga();

        String[] s = {"**** FM_APP ****\n", "Adicionar Jogador", "Adicionar Equipa", "Mudar Jogador de Equipa", "Consultar Equipa", "Consultar Jogador", "Carregar Estado De Um Ficheiro"};
        Menu start = new Menu(s);
        Jogador j;
        String jogadorConsultar = "";
        String equipa = "";
        int getOpt = -1;
        while(getOpt != 0){
            start.execute();
            getOpt = start.getOption();
            switch (getOpt){
                case 1:
                    String jogador;
                    String[] sjogador;
                    try{
                        Map.Entry<String, Jogador> entry = FMView.getPlayer();
                        j = entry.getValue();
                        equipa = entry.getKey();
                    }
                    catch(CamposInvalidos e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    try{
                        l.addJogador(j, equipa);
                        System.out.println(l.getJogadorLiga(equipa, j));
                    }
                    catch(EquipaNaoExisteException | JogadorNaoExiste | NumeroNaoDisponivel e){
                        System.out.println(e.getMessage());
                    }
                    //System.out.println(l.getEquipa(equipa));
                    break;
                case 2:
                    equipa = FMView.getEquipa();
                    l.adicionaEquipa(equipa);
                    //System.out.println(l.getEquipa(equipa));
                    break;
                case 3:
                    Map.Entry<String, Map.Entry<String, String>> transf = FMView.getTransferencia();
                    Equipa origem = null;
                    Equipa destino = null;
                    try{
                        origem = l.getEquipa(transf.getValue().getKey());
                        destino = l.getEquipa(transf.getValue().getValue());
                        //System.out.println(origem.toString());
                        //System.out.println(destino.toString());
                        l.transferencia(transf);
                    }
                    catch(NumeroNaoDisponivel | EquipaNaoExisteException | JogadorNaoExiste e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println(origem.toString());
                    System.out.println(destino.toString());
                    break;
                case 4:
                    equipa = FMView.consultarEquipa();
                    try{
                        FMView.displayEquipa(l.getEquipa(equipa));
                    }
                    catch(EquipaNaoExisteException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    jogadorConsultar = FMView.consultarJogador();
                    try{
                        FMView.displayJogador(l.consultaJogador(jogadorConsultar));
                    }
                    catch(JogadorNaoExiste e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    Parser p;
                    try {
                        System.out.println("correu");
                        p = new Parser();
                        p.parse();
                        l.setEquipas(p.getEquipas());
                        l.setJogos(p.getJogos());
                        //System.out.println(l.toString());
                        //System.out.println(l.getEquipa("Mendelssohn F. C."));
                        //System.out.println(l.getJogos());
                    }
                    catch (LinhaIncorretaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    break;
            }
        }


    }
}
