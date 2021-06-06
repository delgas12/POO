package com.company;

import java.util.Arrays;
import java.util.List;
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

        String[] s = {"**** FM_APP ****\n", "Adicionar Jogador", "Adicionar Equipa", "Mudar Jogador de Equipa", "Consultar Equipa", "Consultar Jogador", "Calcular Habilidade Jogador", "Calcular Habilidade Equipa","Jogo","Carregar Estado De Um Ficheiro"};
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
                    l.criaEquipa(equipa);
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
                    String jogadorHabilidade = FMView.getPlayerForHability();
                    try{
                        FMView.mostraHabilidade(l.consultaJogador(jogadorHabilidade).getHabilidade());
                    }
                    catch(JogadorNaoExiste e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    equipa = FMView.calculaHabilidade();
                    try{
                        Equipa procura = l.getEquipa(equipa);
                        int habilidade = procura.calculaHabilidadeEquipa();
                        String tatica = "";
                        if (habilidade == 0){
                            tatica = FMView.pedeAlinhamento();
                            String[] posicoes = tatica.split("-");
                            procura.criaInicial(Integer.parseInt(posicoes[0]), Integer.parseInt(posicoes[1]), Integer.parseInt(posicoes[2]), Integer.parseInt(posicoes[3]));
                            procura.calculaHabilidadeEquipa();
                            l.adicionaEquipa(procura);
                        }
                        FMView.displayHabilidadeEquipa(procura.getHabilidade());
                    }
                    catch (EquipaNaoExisteException | InsufficientPlayers e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    //chamar o metodo do jogo
                    menuJogo(l);
                    break;
                case 9:
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
                    catch (LinhaIncorretaException | InsufficientPlayers e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    break;
            }
        }
    }

    private static void menuJogo(Liga l) {
        String[] s = {"**** Menu Jogo ****\n", "Escolher Equipas", "Escolher Titulares Casa" , "Escolher Titulares Fora", "Jogar"};
        Menu jogo = new Menu(s);
        Jogo j = new Jogo();
        Equipa casa = null;
        Equipa fora = null;
        int getOpt = -1;
        while(getOpt != 0) {
            jogo.execute();
            getOpt = jogo.getOption();
            switch (getOpt) {
                case 1:
                    Map.Entry<String, String> equipas = FMView.escolherEquipas();
                    try{
                        casa = l.getEquipa(equipas.getKey());
                        fora = l.getEquipa(equipas.getValue());
                        j.setEquipaCasa(casa);
                        j.setEquipaFora(fora);
                    }
                    catch(EquipaNaoExisteException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    if (casa != null){
                        j.setJogadoresCasa(FMView.mostraJogadores(j.getEquipaCasa()));
                        j.adicionaTitularesCasaJogo();
                    }
                    else FMView.erros(1);

                    break;
                case 3:
                    if (fora != null){
                        j.setJogadoresFora(FMView.mostraJogadores(j.getEquipaFora()));
                        j.adicionaTitularesForaJogo();
                    }
                    else FMView.erros(2);
                    break;
                case 4:
                    if (casa == null){
                        FMView.erros(1);
                    }
                    else if (fora == null){
                        FMView.erros(2);
                    }
                    else if (j.getJogadoresCasa().size() == 0){
                        FMView.erros(3);
                    }
                    else if (j.getJogadoresFora().size() == 0){
                        FMView.erros(4);
                    }
                    else{

                    }
                    break;
            }
        }


    }
}
