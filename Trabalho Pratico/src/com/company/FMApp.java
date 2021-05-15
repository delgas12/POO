package com.company;

import java.util.Arrays;

public class FMApp {
    public static void main(String[] args){
        String[] s  = {"**** FM_APP ****\n", "Adicionar Jogador", "Carregar Estado De Um Ficheiro"};
        Menu start = new Menu(s);
        Liga l = new Liga();
        Jogador j;
        start.execute();
        switch (start.getOption()){
            case 1:
                String jogador;
                String[] sjogador;
                try{ j = FMView.getPlayer();}
                catch(CamposInvalidos e){
                    System.out.println(e.getMessage());
                    break;
                }
                System.out.println(j);
                l.addJogador(j);
                break;
            case 2:
                break;
            case 0:
                break;
        }

    }
}
